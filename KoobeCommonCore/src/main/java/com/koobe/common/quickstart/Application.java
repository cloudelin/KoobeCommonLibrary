package com.koobe.common.quickstart;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.core.config.KoobeConfig;

/**
 *
 * @author lyhcode
 */
public class Application {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Hello Koobe");
        
        KoobeApplication app = KoobeApplication.getInstance();
        
        KoobeConfig config = app.getConfig();
        
        config.printAllConfigValues();
    }
}
