/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.converter.impl;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lyhcode
 */
public class PoiConverter extends GeneralConverter {
    
    private final static Logger log = LoggerFactory.getLogger(PoiConverter.class);
        
    {
        //srcType = FileType.DOC;
    }
    
    public PoiConverter(FileType srcType, FileType destType) {
        this.srcType = srcType;
        this.destType = destType;
    }

    @Override
    public boolean convert(File src, File dest) {
        
        if (dest.isFile()) {
            log.warn("File {} exists and will be overwrite.", dest);
        }
        
        return dest.isFile();
    }
}
