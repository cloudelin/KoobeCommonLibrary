/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.util;

/**
 *
 * @author lyhcode
 */
public class SystemEnvironment {
    
    private final static String osName;
    private final static String osVersion;
    private final static String osArch;
    private final static int bitness;
    
    static {
        osName = System.getProperty("os.name");
        osVersion = System.getProperty("os.version");
        osArch = System.getProperty("os.arch");
        
        // 32 or 64 (bit)
        bitness = Integer.parseInt(System.getProperty("sun.arch.data.model"));
    }
    
    /**
     *
     * @return
     */
    public static boolean isX86() {
        return bitness == 32;
    }
    
    /**
     *
     * @return
     */
    public static boolean isX64() {
        return bitness == 64;
    }
    
    /**
     *
     * @return
     */
    public static int getBitness() {
        return bitness;
    }
    
    /**
     *
     * @return
     */
    public static boolean isWindows() {
        return osName.toLowerCase().startsWith("windows");
    }
    
    /**
     *
     * @return
     */
    public static boolean isLinux() {
        return osName.toLowerCase().startsWith("linux");
    }
    
    /**
     *
     * @return
     */
    public static boolean isMac() {
        return osName.toLowerCase().startsWith("mac");
    }
    
    /**
     *
     * @return
     */
    public static String getSystemInfo() {
        return osName + ", " + osArch + ", " + osVersion;
    }

    /**
     * Is Amazon EC2 Linux?
     *
     * @return true, or false
     */
    public static boolean isAws() {
        return osVersion.toLowerCase().contains(".amzn");
    }

}
