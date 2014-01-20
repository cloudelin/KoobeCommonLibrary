package com.koobe.common.storage.impl;

import org.apache.commons.io.IOUtils;
import org.jets3t.service.S3Service;
import org.jets3t.service.S3ServiceException;
import org.jets3t.service.ServiceException;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Amazon S3 Storage Wrapper
 */
public class S3Storage implements KoobeStorage {

    private final static Logger log = LoggerFactory.getLogger(S3Storage.class);

    private AWSCredentials awsCredentials;

    private String bucketName;

    private S3Service s3service;

    public S3Storage(AWSCredentials awsCredentials) {

        this.awsCredentials = awsCredentials;

        s3service = getS3Service();
    }

    /**
     * set default bucket name
     *
     * @param bucketName
     */
    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    /**
     * Upload contents from input stream to remote s3 bucket.
     *
     * @param path new object path in a s3 bucket
     * @param inputStream source input stream
     */
    @Override
    public void putFile(String path, InputStream inputStream) {
        putFile(path, inputStream, null);
    }

    /**
     * Upload contents from input stream to remote s3 bucket. Content type specified.
     *
     * @param path new object path in a s3 bucket
     * @param inputStream source input stream
     * @param contentType specified content type
     */
    public void putFile(String path, InputStream inputStream, String contentType) {
        try {

            S3Object obj;
            obj = new S3Object(path);

            obj.setDataInputStream(inputStream);

            if (contentType != null) {
                obj.setContentType(contentType);
            }

            log.info("Upload input stream to remote s3 bucket {}", bucketName);

            s3service.putObject(bucketName, obj);

        } catch (S3ServiceException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Upload a local file to s3 bucket.
     *
     * @param path path in a s3 bucket
     * @param file local file object
     */
    @Override
    public void putFile(String path, File file) {
        putFile(path, file, null);
    }

    /**
     * Upload a local file to s3 bucket. Specified content type.
     *
     * @param path path in a s3 bucket
     * @param file local file object
     */
    public void putFile(String path, File file, String contentType) {

        try {

            S3Object obj;
            obj = new S3Object(path);

            obj.setDataInputFile(file);

            if (contentType != null) {
                obj.setContentType(contentType);
            }

            log.info("Upload local file {} to remote s3 bucket {}", file, bucketName);

            s3service.putObject(bucketName, obj);

        } catch (S3ServiceException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Check s3 object exists.
     *
     * @param path object key in the s3 bucket.
     * @return true if object exists, false if not.
     */
    @Override
    public boolean hasFile(String path) {
        try {
            S3Object obj = s3service.getObject(bucketName, path);
            if (obj.getContentLength() > 0) {
                return true;
            }
        } catch (S3ServiceException e) {
            log.error(e.getMessage());
        }
        return false;
    }

    /**
     * Remove a file with specified path in s3 bucket.
     *
     * @param path object key in the s3 bucket.
     */
    @Override
    public void removeFile(String path) {
        try {
            s3service.deleteObject(bucketName, path);
        } catch (ServiceException e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Get input stream from object path
     *
     * @param path
     * @return the input stream from s3 object, or null if not available.
     */
    @Override
    public InputStream getFileInputStream(String path) {
        try {
            S3Object obj = s3service.getObject(bucketName, path);
            return obj.getDataInputStream();
        } catch (ServiceException e) {
            log.error(e.getErrorMessage());
        }
        return null;
    }

    /**
     * Get file from object path
     *
     * @param path
     * @return the file contains object contents, or null if not available.
     */
    @Override
    public File getFile(String path) {
        InputStream inputStream = getFileInputStream(path);
        File srcFile = new File(path);

        File destFile = null;

        try {
            destFile = File.createTempFile(srcFile.getName(), "." + getSuffixName(srcFile.getName()));

            OutputStream outputStream = new FileOutputStream(destFile);

            IOUtils.copy(inputStream, outputStream);

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return destFile;
    }
    
    @Override
	public boolean objectExists(String path) {
    	try {
            S3Object obj = s3service.getObject(bucketName, path);
            if (obj != null) {
                return true;
            }
        } catch (S3ServiceException e) {
            log.error(e.getMessage());
        }
        return false;
	}

    /**
     * Extract suffix name from a file name. (e.g. suffix for test.pdf is pdf)
     *
     * @param fileName original file name
     * @return suffix for file name
     */
    private String getSuffixName(String fileName) {
        String suffix = "";

        int pos = fileName.lastIndexOf(".");

        if (pos >= 0) {
            suffix = fileName.substring(pos + 1);
        }

        return suffix;
    }


    /**
     * Is bucket accessible?
     *
     * @return true or false
     */
    @Override
    public boolean isAccessible() {

        if (s3service == null) {
            return false;
        }

        try {
            return s3service.isBucketAccessible(bucketName);
        } catch (ServiceException e) {
            log.error(e.getErrorMessage());
            return false;
        }
    }

    /**
     * S3 Service from JetS3t
     *
     * @return s3 service instance
     */
    private S3Service getS3Service() {
        try {

            S3Service s3service = new RestS3Service(awsCredentials);

            log.info("S3 service instance created");

            return s3service;

        } catch (S3ServiceException e) {

            log.error(e.getS3ErrorMessage());
        }
        return null;
    }

	

}
