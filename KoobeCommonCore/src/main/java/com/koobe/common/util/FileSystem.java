/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lyhcode
 */
public class FileSystem {

    private final static Logger log = LoggerFactory.getLogger(FileSystem.class);

    /**
     * Copy stream data to a file
     *
     * @param srcStream
     * @param destFile
     * @throws IOException
     */
    public static void copyFile(InputStream srcStream, File destFile) throws IOException {
        if (srcStream == null) {
            throw new FileNotFoundException();
        }

        OutputStream out;
        out = new FileOutputStream(destFile);

        log.info("Copy file from input stream to {}.", destFile);

        byte[] buf = new byte[1024];
        int len;
        while ((len = srcStream.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        out.close();
    }

    /**
     * Copy file
     *
     * @param srcFile
     * @param destFile
     * @throws IOException
     */
    public static void copyFile(File srcFile, File destFile) throws IOException {
        if (!srcFile.isFile()) {
            throw new FileNotFoundException();
        }

        OutputStream out = new FileOutputStream(destFile);
        InputStream in = new FileInputStream(srcFile);

        log.info("Copy file from {} to {}.", srcFile, destFile);

        byte[] buf = new byte[1024];
        int len;
        while ((len = in.read(buf)) > 0) {
            out.write(buf, 0, len);
        }
        
        in.close();
        out.close();

    }
}
