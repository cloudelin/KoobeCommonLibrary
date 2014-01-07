/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.converter;

import java.io.File;

/**
 *
 * @author lyhcode
 */
public interface KoobeConverter {
    public void setSrcType(KoobeFileType type);
    public KoobeFileType getSrcType();
    public void setDestType(KoobeFileType type);
    public KoobeFileType getDestType();
    
    public boolean convert(File src, File target);
}
