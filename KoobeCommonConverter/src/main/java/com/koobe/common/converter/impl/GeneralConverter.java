/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.converter.impl;

import com.koobe.common.converter.KoobeFileType;
import com.koobe.common.converter.KoobeConverter;

/**
 *
 * @author lyhcode
 */
public abstract class GeneralConverter implements KoobeConverter {
    
    protected KoobeFileType srcType = KoobeFileType.NONE;
    protected KoobeFileType destType = KoobeFileType.NONE;

    @Override
    public void setSrcType(KoobeFileType type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KoobeFileType getSrcType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDestType(KoobeFileType type) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public KoobeFileType getDestType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
