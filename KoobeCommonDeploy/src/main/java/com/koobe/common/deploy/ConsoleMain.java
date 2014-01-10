package com.koobe.common.deploy;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.deploy.deployer.KoobeDeployer;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

/**
 *
 * @author lyhcode
 */
public class ConsoleMain {
    
    public static void main(String[] args) {
        System.out.println("Koobe AWS Elasti BeansTalk Deployer");
        
        KoobeApplication application = KoobeApplication.getInstance();
        
        KoobeDeployService service = (KoobeDeployService)application.getService("koobeDeployService");
        
        KoobeDeployer deployer = service.getBeansTalkDeployer("KoobeQA", "koobeqa-test");
        
        deployer.deploy(getBundleWarFile());
    }

    private static File getBundleWarFile() {
        URL bundleWarUrl = ClassLoader.getSystemResource("GradleTinyWar.war");
        
        try {
            return new File(bundleWarUrl.toURI());
        } catch (URISyntaxException ex) {
            //nothing
        }
        
        return null;
    }
}
