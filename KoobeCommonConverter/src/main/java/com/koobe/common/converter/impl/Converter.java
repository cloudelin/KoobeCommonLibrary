/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.converter.impl;

import java.io.File;

/**
 *
 * @author lyhcode
 */
public interface Converter {
    public void setSrcType(FileType type);
    public FileType getSrcType();
    public void setDestType(FileType type);
    public FileType getDestType();
    
    public boolean convert(File src, File target);
}
