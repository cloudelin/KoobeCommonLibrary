package com.koobe.pdf2epub;

/**
 *
 * @author arthur
 */
public class Main {
    static final String version = "1.00";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            usage();
            return;
	}
		
	int quality = 80;
	int progressive = 1;
	int dpi = 144;
		
	for (int i=2; i<args.length; i++) {
            if (args[i].startsWith("-q=")) {
		quality = Integer.parseInt(args[i].substring(3));
            }
            else if (args[i].startsWith("-p=")) {
		progressive = Integer.parseInt(args[i].substring(3));
            }
            else if (args[i].startsWith("-d=")) {
		dpi = Integer.parseInt(args[i].substring(3));
            }
            else if (args[i].equals("-v")) {
		System.out.println("\n\nKepConverter Version " + version + " Copyeright Koobe Technology Inc. 2012.\n");
            }
            else if (args[i].equals("-u")) {
		usage();
            }
	}

        Pdf2ePub converter = new Pdf2ePub(args[0], args[1], quality, progressive, dpi);
        converter.start();
    }

    /* (non-Java-doc)
     * @see java.lang.Object#Object()
     */
    public Main() {
	super();
    }

    public static void usage() {
	System.out.println(
		"\n\nPdf2ePub Version " + version + " Copyeright Koobe Technology Inc. 2013.\n" +
		"USAGE: java -Xmx2G -jar KepConverter.jar {srcPath} {destPath} [OPTIONS]\n" +
		"\tsrcPath\t\tThe PDF source path.\n" +
		"\tdestPath\t\tThe destination path of output of converted epub files.\n" +
		"\tOPTIONS:\n" +
		"\t\t-q=[n]        Set JPEG quality, n is the number between 1-100. Default is 80.\n" +
		"\t\t-p=[n]        Set to generate progressive JPEG if n is 1. Default is 1.\n" +
		"\t\t-d=[n]        Set DPI of JPEG, the default value is 72. Double DPI, ex, 144 will cause double size of each page." +
		"\t\t-v            Print version of Pdf2ePub.\n" +
		"\t\t-u            Print usage of Pdf2ePub.\n" +
		"\t\t-x            Extract epub to be a file directory.\n" +
		"\n\n" +
		"Examples: java -Xmx2G -jar Pdf2ePub.jar c:\\kep c:\\epub -q=100 -p=1 -d=144\n"
        );
    }
 }
