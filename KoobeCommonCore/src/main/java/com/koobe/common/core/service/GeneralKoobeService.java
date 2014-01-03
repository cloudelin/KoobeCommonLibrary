/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.core.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lyhcode
 */
public class GeneralKoobeService implements KoobeService {
    
    /**
     * Service available or not
     */
    private boolean available = true;

    /**
     * Check service is available or not
     */
    @Override
    public boolean isAvailable() {
        // Default always be TRUE
        return available;
    }
    
    /**
     * Set service available status
     *
     * @param available true or false
     */
    protected void setAvailable(boolean available) {
        this.available = available;
    }
    
}
