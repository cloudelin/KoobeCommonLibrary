/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.converter.impl;

import com.koobe.pdf2epub.Pdf2ePub;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lyhcode
 */
public class MupdfConverter extends GeneralConverter {
    
    private final static Logger log = LoggerFactory.getLogger(MupdfConverter.class);
    
    private int quality = 80;
    private int progressive = 1;
    private int dpi = 144;
    
    {
        srcType = FileType.PDF;
    }
    
    public MupdfConverter(FileType destType) {
        this.destType = destType;
    }

    @Override
    public boolean convert(File src, File dest) {
        
        if (dest.isFile()) {
            log.warn("File {} exists and will be overwrite.", dest);
        }
        
        Pdf2ePub converter = new Pdf2ePub(src, dest, quality, progressive, dpi);
        converter.start();
        
        return dest.isFile();
    }
    
    public int getQuality() {
        return quality;
    }
    
    public void setQuality(int quality) {
        this.quality = quality;
    }
    
    public int getProgressive() {
        return progressive;
    }
    
    public void setProgressive(int progressive) {
        this.progressive = progressive;
    }
    
    public int getDpi() {
        return dpi;
    }
    
    public void setDpi(int dpi) {
        this.dpi = dpi;
    }
}
