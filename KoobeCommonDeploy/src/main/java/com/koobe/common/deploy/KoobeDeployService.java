package com.koobe.common.deploy;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.core.service.GeneralKoobeService;
import com.koobe.common.deploy.deployer.impl.BeansTalkDeployer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Deploy WAR to AWS ElastiBeansTalk
 *
 * @author lyhcode
 */
@Service
public class KoobeDeployService extends GeneralKoobeService {

    @Autowired
    KoobeApplication application;

    public BeansTalkDeployer getBeansTalkDeployer(String applicationName, String environmentName) {
        String accessKey = application.getConfig().getAwsAccessKeyID();
        String secretKey = application.getConfig().getAwsSecretKey();

        return new BeansTalkDeployer(accessKey, secretKey, applicationName, environmentName);
    }
}
