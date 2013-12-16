/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.core.config.reader;

/**
 *
 * @author lyhcode
 */
public class SystemEnvironmentConfigReader extends PropertyBasedConfigReader implements ConfigReader {

    /**
     *
     */
    public SystemEnvironmentConfigReader() {
        setProperties(System.getProperties());
    }
}
