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
    public void setSrcType(KoobeFileType srcType) {
        this.srcType = srcType;
    }

    @Override
    public KoobeFileType getSrcType() {
        return srcType;
    }

    @Override
    public void setDestType(KoobeFileType destType) {
        this.destType = destType;
    }

    @Override
    public KoobeFileType getDestType() {
        return destType;
    }
    
}
