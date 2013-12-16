/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.deploy.deployer;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalkClient;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationVersionRequest;
import com.amazonaws.services.elasticbeanstalk.model.CreateApplicationVersionResult;
import com.amazonaws.services.elasticbeanstalk.model.S3Location;
import com.amazonaws.services.elasticbeanstalk.model.UpdateEnvironmentRequest;
import com.amazonaws.services.elasticbeanstalk.model.UpdateEnvironmentResult;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lyhcode
 */
public class BeansTalkDeployer implements KoobeDeployer {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final String accessKey;
    private final String secretKey;

    private final String applicationName;
    private final String environmentName;

    private final AWSElasticBeanstalkClient elasticBeanstalk;
    
    private final static String DEFAULT_ENDPOINT = "https://elasticbeanstalk.ap-southeast-1.amazonaws.com";

    public BeansTalkDeployer(String accessKey, String secretKey, String applicationName, String environmentName) {

        this.accessKey = accessKey;
        this.secretKey = secretKey;

        this.applicationName = applicationName;
        this.environmentName = environmentName;
        
        elasticBeanstalk = getBeansTalkClient();
        
        elasticBeanstalk.setEndpoint(DEFAULT_ENDPOINT);
    }

    @Override
    public void deploy(String appWarFileName) {
        
        File appWarFile = new File(appWarFileName);
        
        if (appWarFile.isFile()) {
            deploy(appWarFile);
        }
        else {
            log.error("File {} not found.", appWarFile.getPath());
        }
    }

    @Override
    public void deploy(File appWarFile) {

        log.info("Finding S3 bucket to upload WAR");

        String bucketName = elasticBeanstalk.createStorageLocation().getS3Bucket();

        String s3key = uniqueTempWarFileName(appWarFile);
        uploadToS3(appWarFile, bucketName, s3key);
        
        String versionLabel = getVersionLabel(appWarFile);
        

        log.info("Create application version with uploaded application");

        CreateApplicationVersionRequest cavr = new CreateApplicationVersionRequest();
        cavr.setApplicationName(applicationName);
        cavr.setVersionLabel(versionLabel);
        cavr.setDescription(getDescription());
        cavr.setAutoCreateApplication(true);
        cavr.setSourceBundle(new S3Location(bucketName, s3key));
        
        CreateApplicationVersionResult result;
        result = elasticBeanstalk.createApplicationVersion(cavr);
        
        log.info("Created application version {}", result);
        
        
        
        
        log.info("Updating environment with uploaded application version");
        
        UpdateEnvironmentRequest uer;
        uer = new UpdateEnvironmentRequest();
        
        uer.setEnvironmentName(environmentName);
        uer.setVersionLabel(versionLabel);
        
        UpdateEnvironmentResult result2;
        result2 = elasticBeanstalk.updateEnvironment(uer);
        
        log.info("Updated environment {}", result2);
    }
    
    @Override
    public void debug() {
        System.out.println(elasticBeanstalk.describeApplications());
        System.out.println(elasticBeanstalk.describeApplicationVersions());
        System.out.println(elasticBeanstalk.describeEnvironments());
    }

    private AWSElasticBeanstalkClient getBeansTalkClient() {
        return new AWSElasticBeanstalkClient(getAwsCredentials());
    }

    private AWSCredentials getAwsCredentials() {
        if (!hasAccessKey()) {
            log.error("AWSCredentials required access key settings.");
            return null;
        }

        return new BasicAWSCredentials(accessKey, secretKey);
    }

    private boolean hasAccessKey() {
        return accessKey != null && !accessKey.isEmpty() && secretKey != null && !secretKey.isEmpty();
    }

    private String getVersionLabel(File warFile) {
        return getWarTimestamp(warFile);
    }

    private String getWarTimestamp(File warFile) {
        Date warDate = new Date(warFile.lastModified());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

        return df.format(warDate);
    }

    private String getDescription() {
        return "Deployed by Koobe Deploy Service";
    }

    private String uniqueTempWarFileName(File warFile) {
        String uuid = Long.toHexString(warFile.lastModified());
        return uuid + "-" + warFile.getName();
    }

    private void uploadToS3(File file, String bucketName, String key) {
        log.info("Uploading local WAR file {} to remote WAR file: {}", file.getName(), key);

        try {
            String s3key = URLEncoder.encode(key, "UTF-8");
            AmazonS3 s3 = new AmazonS3Client(getAwsCredentials());
            s3.putObject(bucketName, s3key, file);
        } catch (UnsupportedEncodingException ex) {
            log.error("Upload file to s3 failed.");
            log.error(ex.getMessage());
        }
    }
}
