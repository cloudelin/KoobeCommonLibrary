package com.koobe.pdf2epub;

import com.koobe.common.util.FileSystem;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.epub.EpubWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author arthur
 */
public class Pdf2ePub {

    private final Logger log = LoggerFactory.getLogger(getClass());

    public native long createBookInstance(String srcKep);

    public native int pageCount(long instance);

    public native byte[] renderPage(long instance, int page, int progreesive, int compressRatio, int dpi);

    public native byte[] renderThumbnail(long instance, int page, int compressRatio);

    public native void closeBookInstance(long instance);

    public native PageText[] getPageText(long instance, int page);

    public native com.koobe.pdf2epub.PageText[] pageTextSpans(long instance, int page);
    private String srcPath, destPath;
    private Map<String, FileCopyThread> copyThreads = new HashMap<String, FileCopyThread>();
    private Object lock = new Object();
    private int quality;
    private int progressive;
    private int dpi;

    public Pdf2ePub(File srcFile, File destFile, int quality, int progressive, int dpi) {
        this(srcFile.getAbsolutePath(), destFile.getAbsolutePath(), quality, progressive, dpi);
    }

    /**
     *
     * @param srcPath The PDF source path.
     * @param destPath The destination path of output of converted epub files.
     * @param quality JPEG quality, it's a number between 1-100. Default is 80.
     * @param progressive Generate progressive JPEG if it is 1. Default is 1.
     * @param dpi DPI of JPEG, the default value is 72. Double DPI, ex, 144 will
     * cause double size of each page.
     */
    public Pdf2ePub(String srcPath, String destPath, int quality, int progressive, int dpi) {
        File src;
        File dest;
        // checking src path exists
        src = new File(srcPath);
        if (!src.exists()) {
            System.out.println("The source path does not exist.");
        } else if (srcPath.toLowerCase().endsWith(".kep")) {
            System.out.println("The source is not PDF file.");
        } else {
            // checking dest path exists. If it does not exist, we create it.
            dest = new File(destPath);
            if (dest.exists()) {
                System.out.println("The destination file exists, delete it now.");
                if (!dest.delete()) {
                    System.out.println("Delete destination file failed.");
                }
            }
        }

        this.srcPath = srcPath;
        this.destPath = destPath;
        this.quality = quality;
        this.progressive = progressive;
        this.dpi = dpi;
    }

    public void start() {
        System.out.println("Conversion: " + srcPath);
        Map<Integer, List<PageTextObject>> textSpans = new HashMap<Integer, List<PageTextObject>>();

        // creating destination epub
        EpubWriter epub = new EpubWriter();
        Book epubBook = new Book();

        try {
            long bookit;
            int count;
            // initialize bookit
            bookit = createBookInstance(srcPath);
            if (bookit == 0) {
                System.out.println("Cannot open PDF");
            }

            // get page count
            count = pageCount(bookit);
            for (int i = 1; i <= count; i++) {
                // write thumbnail
                Resource thumbnail = null;
                // render thumbnail now
                byte[] tb;
                tb = renderThumbnail(bookit, i, 70);
                if (tb != null) {
                    String hrefImg = "images/" + i + "thumbnail" + String.format("%04d", i) + ".jpg";
                    epubBook.getResources().add(new Resource(tb, hrefImg, ZipEntry.STORED));
                }
                tb = null;

                /*
                 // write mp3
                 Resource mp3 = book.getResources().getById("MP3_" + String.format("%04d", i - 1));
                 if (mp3 != null) {
                 epubBook.getResources().add(mp3);
                 }
                 mp3 = null;
                 */
                // get text elements
                if (Setting.USE_MUPDF_JNI) {
                    com.koobe.pdf2epub.PageText[] pageTexts = null;
                    pageTexts = pageTextSpans(bookit, i);
                    if (pageTexts != null) {
                        System.out.println("Count of text span : " + pageTexts.length);
                        List<PageTextObject> ptoArray = new ArrayList<PageTextObject>();
                        for (com.koobe.pdf2epub.PageText t : pageTexts) {
                            PageTextObject pto = new PageTextObject();
                            pto.setTextSpan(t.getX0(), t.getX1(), t.getY0(), t.getY1(), t.isEndOfLine(), t.getText());
                            ptoArray.add(pto);
                            pto = null;
                        }
                        textSpans.put(i, ptoArray);
                        ptoArray = null;
                    }
                    pageTexts = null;
                }

                // retrieve jpeg page by page
                byte[] jpeg = null;
                jpeg = renderPage(bookit, i, progressive, quality, dpi);

                if (jpeg != null) {
                    // adding image resource
                    System.out.println("\t...Adding page " + i + " to epub. length=" + jpeg.length);
                    String hrefImg = "images/" + i + ".jpg";
                    epubBook.getResources().add(new Resource(jpeg, hrefImg, ZipEntry.STORED));
                    // adding html page
                    String html = "<!DOCTYPE html>\n<html>\n<body>\n<img src='../" + hrefImg + "'></img>\n</body>\n</html>";
                    epubBook.addSection("page_" + i, new Resource(html.getBytes("UTF-8"), "xhtml/" + i + ".html"));
                }
                jpeg = null;
            }

            // add text span section
            if (Setting.USE_MUPDF_JNI) {
                closeBookInstance(bookit);
                if (Setting.USE_MUPDF_JNI) {
                    String span = JsonObject.toJson(textSpans);
                    textSpans.clear();
                    epubBook.addSection("TextSpans", new Resource(span.getBytes("UTF-8"), "resources/textspans.txt"));
                }
            } else {
                String span = JsonObject.toJson(textSpans);
                textSpans.clear();
                epubBook.addSection("TextSpans", new Resource(span.getBytes("UTF-8"), "resources/textspans.txt"));
            }

            File pdf = new File(srcPath);
            File epubOut = new File(destPath);

            // Create temporary file for write epub outputs
            File tmpFile = File.createTempFile(pdf.getName(), ".epub");

            epub.write(epubBook, new FileOutputStream(tmpFile));
            /*
             while (copyThreads.size() >= 1) {
             System.out.println("Too many threads are in copy state. Wait for a slot to be released.");
             Thread.sleep(60*1000);
             }
             System.out.println("Finished one thread at least. Continue...");
             FileCopyThread ct = new FileCopyThread(kep.getName(), new File(epubName), new File(toFolder + "\\" + kep.getName().replace(".kep", ".epub")));
             copyThreads.put(kep.getName(), ct);
             ct.start();
             */
            FileSystem.copyFile(tmpFile, epubOut);
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class FileCopyThread extends Thread {

        private File in;
        private File out;
        private String key;

        public FileCopyThread(String key, File in, File out) {
            this.in = in;
            this.out = out;
            this.key = key;
        }

        @Override
        public void run() {
            try {
                FileSystem.copyFile(in, out);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                copyThreads.remove(key);
            }
        }
    }
}
