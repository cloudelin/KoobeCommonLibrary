package nl.siegmann.epublib.epub;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import nl.siegmann.epublib.Constants;
import nl.siegmann.epublib.domain.Book;
import nl.siegmann.epublib.domain.MediaType;
import nl.siegmann.epublib.domain.Resource;
import nl.siegmann.epublib.domain.Resources;
import nl.siegmann.epublib.service.MediatypeService;
import nl.siegmann.epublib.util.ResourceUtil;
import nl.siegmann.epublib.util.StringUtil;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipInputStream;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Reads an epub file.
 *
 * @author paul
 *
 */
public class EpubReader {

    protected String packageResourceHref = "OEBPS/container.opf";
    private BookProcessor bookProcessor = BookProcessor.IDENTITY_BOOKPROCESSOR;
    private final static String[] encodingSupported = new String[] {
        "Big5",
        "GB18030",
        "ISO-2022-JP",
        "SHIFT_JIS",
        "EUC-JP",
        "WINDOWS-1252",
        "ISO-8859-1"
    };

    public Book readEpub(InputStream in) throws IOException {
        return readEpub(in, Constants.ENCODING);
    }

    public Book readEpub(ZipInputStream in) throws IOException {
        return readEpub(in, Constants.ENCODING);
    }

    /**
     * Read epub from inputstream
     *
     * @param in the inputstream from which to read the epub
     * @param encoding the encoding to use for the html files within the epub
     * @return
     * @throws IOException
     */
    public Book readEpub(InputStream in, String encoding) throws IOException {
        Book book = null;
        int idx = 0;
        int c;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((c=in.read()) != -1)
            baos.write(c);
        
        while (true) {
            try {
                ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
                ZipInputStream zis = new ZipInputStream(bais, encoding);
                book = readEpub(zis, encoding);
            } catch (IOException e) {
                encoding = encodingSupported[idx++];
                continue;
            }
            break;
        }
        
        return book;
    }

    /**
     * Reads this EPUB without loading all resources into memory.
     *
     * @param fileName the file to load
     * @param encoding the encoding for XHTML files
     * @param lazyLoadedTypes a list of the MediaType to load lazily
     * @return
     * @throws IOException
     */
    public Book readEpubLazy(String fileName, String encoding, List<MediaType> lazyLoadedTypes) throws IOException {
        Book result = new Book();
        Resources resources = readLazyResources(fileName, encoding, lazyLoadedTypes);
        handleMimeType(result, resources);
        packageResourceHref = getPackageResourceHref(resources);
        Resource packageResource = processPackageResource(packageResourceHref, result, resources);
        result.setOpfResource(packageResource);
        Resource ncxResource = processNcxResource(packageResource, result);
        result.setNcxResource(ncxResource);
        result = postProcessBook(result);
        return result;
    }
    
    public String getPackageResourceHref() {
        return packageResourceHref;
    }

    /**
     * Reads this EPUB without loading any resources into memory.
     *
     * @param fileName the file to load
     * @param encoding the encoding for XHTML files
     *
     * @return
     * @throws IOException
     */
    public Book readEpubLazy(String fileName, String encoding) throws IOException {
        return readEpubLazy(fileName, encoding, Arrays.asList(MediatypeService.mediatypes));
    }

    public Book readEpub(ZipInputStream in, String encoding) throws IOException {
        Book result = new Book();
        Resources resources = readResources(in, encoding);
        handleMimeType(result, resources);
        String packageResourceHref = getPackageResourceHref(resources);
        Resource packageResource = processPackageResource(packageResourceHref, result, resources);
        if (packageResource != null) {
            result.setOpfResource(packageResource);
            Resource encryptionResource = resources.remove("META-INF/encryption.xml");
            if (encryptionResource != null) {
                result.addResource(encryptionResource);
            }
            Resource ncxResource = processNcxResource(packageResource, result);
            if (ncxResource != null) {
                result.setNcxResource(ncxResource);
            }
            result = postProcessBook(result);
        } else {
            result = null;
        }
        return result;
    }

    protected Book postProcessBook(Book book) {
        if (bookProcessor != null) {
            book = bookProcessor.processBook(book);
        }
        return book;
    }

    protected Resource processNcxResource(Resource packageResource, Book book) {
        return NCXDocument.read(book, this);
    }

    protected Resource processPackageResource(String packageResourceHref, Book book, Resources resources) {
        Resource packageResource = resources.remove(packageResourceHref);
        if (packageResource != null) {
            try {
                PackageDocumentReader.read(packageResource, this, book, resources);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return packageResource;
    }

    protected String getPackageResourceHref(Resources resources) {
        String defaultResult = "OEBPS/content.opf";
        String result = defaultResult;

        Resource containerResource = resources.remove("META-INF/container.xml");
        if (containerResource == null) {
            return result;
        }
        try {
            Document document = ResourceUtil.getAsDocument(containerResource);
            Element rootFileElement = (Element) ((Element) document.getDocumentElement().getElementsByTagName("rootfiles").item(0)).getElementsByTagName("rootfile").item(0);
            result = rootFileElement.getAttribute("full-path");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtil.isBlank(result)) {
            result = defaultResult;
        }
        return result;
    }

    protected void handleMimeType(Book result, Resources resources) {
        resources.remove("mimetype");
    }

    private Resources readLazyResources(String fileName, String defaultHtmlEncoding,
            List<MediaType> lazyLoadedTypes) throws IOException {

        ZipInputStream in = new ZipInputStream(new FileInputStream(fileName));

        Resources result = new Resources();
        Enumeration<ZipEntry> enumEntry = in.getEntries();
        for (ZipEntry zipEntry = enumEntry.nextElement(); zipEntry != null; zipEntry = enumEntry.nextElement()) {
            if (zipEntry.isDirectory()) {
                continue;
            }

            String href = zipEntry.getName();
            MediaType mediaType = MediatypeService.determineMediaType(href);

            Resource resource;

            if (lazyLoadedTypes.contains(mediaType)) {
                resource = new Resource(fileName, zipEntry.getSize(), href);
            } else {
                resource = new Resource(in.getInputStream(zipEntry), href);
            }

            if (resource.getMediaType() == MediatypeService.XHTML) {
                resource.setInputEncoding(defaultHtmlEncoding);
            }
            result.add(resource);
        }

        return result;
    }

    private Resources readResources(ZipInputStream in, String defaultHtmlEncoding) throws IOException {
        Resources result = new Resources();
        ZipEntry zipEntry = null;
        Enumeration<ZipEntry> enumEntry = in.getEntries();

        while (enumEntry.hasMoreElements()) {
            try {
                zipEntry = enumEntry.nextElement();
            } catch (java.lang.IllegalArgumentException e) {
                throw new IOException();
            } catch (java.util.NoSuchElementException e) {
                throw new IOException();
            }
           
            if (zipEntry == null) {
                break;
            }
            if (zipEntry.isDirectory()) {
                continue;
            }
            Resource resource = ResourceUtil.createResource(zipEntry, in.getInputStream(zipEntry));
            if (resource.getMediaType() == MediatypeService.XHTML) {
                resource.setInputEncoding(defaultHtmlEncoding);
            }
            result.add(resource);
        }
        return result;
    }
}
