/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.converter;

import com.koobe.common.core.service.GeneralKoobeService;
import com.koobe.common.converter.impl.MupdfConverter;
import com.koobe.common.util.FileSystem;
import com.koobe.common.util.SystemEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author lyhcode
 */
@Service
public class KoobeConverterService extends GeneralKoobeService {

    private static final Logger log = LoggerFactory.getLogger(KoobeConverterService.class);

    private static final String LIB_PREFIX = "/libjmupdf";
    private static final String LIB_ARCH_X86 = "32";
    private static final String LIB_ARCH_X64 = "64";
    private static final String LIB_OS_WINDOWS = ".dll";
    private static final String LIB_OS_LINUX = ".so";
    private static final String LIB_OS_MAC = ".dylib";

    public KoobeConverterService() {
        setAvailable(false);
        setAvailable(loadLibrary());
    }

    /**
     * Fetch a converter
     *
     * @param type target format e.g. KoobeConverterType.PDF_TO_EPUB
     * @return PDF Converter
     */
    public KoobeConverter getConverter(KoobeConverterType type) {
        if (type.equals(KoobeConverterType.PDF_TO_EPUB)) {
            return new MupdfConverter(KoobeFileType.EPUB);
        }
        return null;
    }

    /**
     * Load libjmupdf library to JVM
     *
     * @return true if library loaded; false if not
     */
    private boolean loadLibrary() {

        // If this service is available, means all required libraries are loaded.
        // Prevent to load same library again
        if (this.isAvailable()) {
            log.warn("Load libjmupdf more than one time is not allow.");
            return true;
        }

        String libPath = getLibPath();

        log.info("Prepare to load JNI native-lib {} into JVM.", libPath);

        if (libPath == null) {
            log.error("No jmupdf support for {}/{}.", SystemEnvironment.getSystemInfo());
            return false;
        }

        InputStream libStream = ClassLoader.getSystemResourceAsStream(libPath);

        if (libStream == null) {
            libStream = getClass().getResourceAsStream(libPath);
        }

        if (libStream == null) {
            libStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(libPath);
        }

        if (libStream == null) {
            log.error("Resource file {} not found.", libPath);
            return false;
        }

        log.info("Found {} in bundled resources.", libPath);

        try {
            File libTemp = File.createTempFile("libjmupdf", getLibExt());

            FileSystem.copyFile(libStream, libTemp);

            // Load the library
            System.load(libTemp.getAbsolutePath());

            log.info("Required library libjmupdf loaded.");

            return true;

        } catch (IOException ex) {
            log.error(ex.getMessage());
        }

        return false;
    }

    /**
     * Get jMuPDF JNI library path for System.load()
     *
     * @return path to libjmupdf* file
     */
    private String getLibPath() {
        String libExt = getLibExt();
        String libBit = null;

        if (SystemEnvironment.isX86()) {
            libBit = LIB_ARCH_X86;
        } else if (SystemEnvironment.isX64()) {
            libBit = LIB_ARCH_X64;
        }

        // For AWS compatible, special version compile with EC2 linux os.
        if (SystemEnvironment.isAws()) {
            return LIB_PREFIX + libBit + "-aws" + libExt;
        }

        return LIB_PREFIX + libBit + libExt;
    }

    /**
     * Library file extension name
     *
     * @return *.dll, *.so or *.dylib
     */
    private String getLibExt() {
        if (SystemEnvironment.isWindows()) {
            return LIB_OS_WINDOWS;
        } else if (SystemEnvironment.isLinux()) {
            return LIB_OS_LINUX;
        } else if (SystemEnvironment.isMac()) {
            return LIB_OS_MAC;
        }
        return null;
    }
}
