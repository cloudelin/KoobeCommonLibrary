/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.converter;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.converter.impl.ConverterType;
import com.koobe.common.converter.impl.MupdfConverter;
import java.io.File;
import java.util.List;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 *
 * @author lyhcode
 */
public class ConsoleMain {

    public static void main(String[] args) {

        System.out.println("Koobe PDF Service");

        KoobeApplication application = KoobeApplication.getInstance();

        KoobeConverterService service;
        service = (KoobeConverterService) application.getService(KoobeConverterService.class);

        if (!service.isAvailable()) {
            System.err.println("Sorry, Koobe PDF Service is not available.");
            System.exit(-1);
        }

        MupdfConverter converter = (MupdfConverter) service.getConverter(ConverterType.PDF_TO_EPUB);

        Options options = new Options();

        // add t option
        options.addOption("q", "quality", true, "Set JPEG quality, n is the number between 1-100. Default is 80.");
        options.addOption("p", "progressive", true, "Set to generate progressive JPEG if n is 1. Default is 1.");
        options.addOption("d", "dpi", true, "Set DPI of JPEG, the default value is 72. Double DPI, ex, 144 will cause double size of each page.");

        options.addOption("v", "version", false, "Print version of Pdf2ePub.");
        options.addOption("h", "help", false, "Print usage of Pdf2ePub.");
        options.addOption("x", "extract", false, "Extract epub to be a file directory.");

        // create the parser
        CommandLineParser parser = new BasicParser();
        try {
            CommandLine cmd = parser.parse(options, args);

            if (cmd.hasOption("h") || args.length==0) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("java -jar pdf2epub.jar [OPTIONS]... [PDF_FILE] [EPUB_FILE]", options);
                System.out.println();
                System.out.println("Examples:");
                System.out.println("java -Xmx2G -jar pdf2epub.jar src.pdf dest.epub -q=100 -p=1 -d=144\n");
            }

            if (cmd.hasOption("v")) {
                System.out.println("pdf2epub v1.0");
                System.out.println();
                System.out.println("Copyeright Koobe Technology Inc. 2012.");
            }

            if (cmd.hasOption("q")) {
                int quality = Integer.parseInt(cmd.getOptionValue("q"));

                converter.setQuality(quality);
            }

            if (cmd.hasOption("d")) {
                int dpi = Integer.parseInt(cmd.getOptionValue("d"));

                converter.setDpi(dpi);
            }

            if (cmd.hasOption("p")) {
                int progressive = Integer.parseInt(cmd.getOptionValue("p"));

                converter.setProgressive(progressive);
            }

            
            List leftArgs = cmd.getArgList();
            
            if (leftArgs.size() >= 2) {

                File srcFile = new File(leftArgs.get(0).toString());
                File destFile = new File(leftArgs.get(1).toString());

                if (srcFile.isFile()) {
                    converter.convert(srcFile, destFile);
                }
                else {
                    System.err.println(srcFile.getPath() + " not found.");
                }
            }

        } catch (ParseException ex) {
            ex.printStackTrace();
        }

    }

}
