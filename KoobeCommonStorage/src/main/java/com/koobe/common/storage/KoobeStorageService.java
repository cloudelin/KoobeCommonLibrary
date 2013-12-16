/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.storage;

import com.koobe.common.core.KoobeApplication;
import com.koobe.common.core.service.GeneralKoobeService;
import com.koobe.common.storage.impl.S3Storage;
import org.jets3t.service.security.AWSCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Deploy WAR to AWS ElastiBeansTalk
 *
 * @author lyhcode
 */
@Service
public class KoobeStorageService extends GeneralKoobeService {

    private final static Logger log = LoggerFactory.getLogger(KoobeStorageService.class);

    @Autowired
    KoobeApplication application;

    public S3Storage getS3Storage() {
        return new S3Storage(getAwsCredentials());
    }

    public S3Storage getS3Storage(String bucketName) {
        S3Storage s3storage = new S3Storage(getAwsCredentials());
        s3storage.setBucketName(bucketName);
        return s3storage;
    }

    private AWSCredentials getAwsCredentials() {

        String awsAccessKey = application.getConfig().getAwsAccessKeyID();
        String awsSecretKey = application.getConfig().getAwsSecretKey();

        log.info("Get AWS credentials with {}", awsAccessKey);

        AWSCredentials awsCredentials;
        awsCredentials = new AWSCredentials(awsAccessKey, awsSecretKey);

        return awsCredentials;
    }
}
