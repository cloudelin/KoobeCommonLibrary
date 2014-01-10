package com.koobe.common.data.security.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rds.AmazonRDSClient;
import com.amazonaws.services.rds.model.AuthorizeDBSecurityGroupIngressRequest;
import com.amazonaws.services.rds.model.RevokeDBSecurityGroupIngressRequest;
import com.koobe.common.core.config.KoobeConfig;
import com.koobe.common.data.security.DatabaseSecurityManager;

/**
 * Created by lyhcode on 2014/1/10.
 */
public class RDSDatabaseSecurityManager implements DatabaseSecurityManager {

    private final static String DEFAULT_ENDPOINT = "https://rds.ap-southeast-1.amazonaws.com";

    private final String accessKey;
    private final String secretKey;
    private final AmazonRDSClient client;

    public RDSDatabaseSecurityManager() {
        KoobeConfig config = KoobeConfig.getInstance();

        accessKey = config.getAwsAccessKeyID();
        secretKey = config.getAwsSecretKey();

        client = getAmazonRDSClient();
    }

    public RDSDatabaseSecurityManager(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;

        client = getAmazonRDSClient();
    }

    /**
     * Allow Address for Security Group
     *
     * @param securityGroupName
     * @param address
     */
    @Override
    public void allow(String securityGroupName, String address) {

        AuthorizeDBSecurityGroupIngressRequest request;
        request = new AuthorizeDBSecurityGroupIngressRequest();

        request.setDBSecurityGroupName(securityGroupName);
        request.setCIDRIP(address);

        client.authorizeDBSecurityGroupIngress(request);
    }

    /**
     * Deny Address for Security Group
     *
     * @param securityGroupName
     * @param address
     */
    @Override
    public void deny(String securityGroupName, String address) {

        RevokeDBSecurityGroupIngressRequest request;
        request = new RevokeDBSecurityGroupIngressRequest();

        request.setDBSecurityGroupName(securityGroupName);
        request.setCIDRIP(address);

        client.revokeDBSecurityGroupIngress(request);
    }

    private AmazonRDSClient getAmazonRDSClient() {
        AmazonRDSClient client = new AmazonRDSClient(getAwsCredentials());
        client.setEndpoint(DEFAULT_ENDPOINT);
        return client;
    }

    private AWSCredentials getAwsCredentials() {
        return new BasicAWSCredentials(accessKey, secretKey);
    }
}
