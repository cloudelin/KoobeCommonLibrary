package com.koobe.common.model.message;

/**
 * Request model of extract epub file for message queue
 * @author cloude
 * @since 2014-1-17
 */
public class ExtractEpubRequest extends GeneralRequest {
	
	public final static String ACTION = "SendExtractEpubRequest";
	
	private String srcBucket;
	private String destBucket;
	private String srcPath;
	private String destPath;
	
	public ExtractEpubRequest() {
		super(ACTION);
	}

	public ExtractEpubRequest(String srcBucket, String destBucket, String srcPath, String destPath) {
		super(ACTION);
		this.srcBucket = srcBucket;
		this.destBucket = destBucket;
		this.srcPath = srcPath;
		this.destPath = destPath;
	}

	public String getSrcBucket() {
		return srcBucket;
	}

	public void setSrcBucket(String srcBucket) {
		this.srcBucket = srcBucket;
	}

	public String getDestBucket() {
		return destBucket;
	}

	public void setDestBucket(String destBucket) {
		this.destBucket = destBucket;
	}

	public String getSrcPath() {
		return srcPath;
	}

	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}

	public String getDestPath() {
		return destPath;
	}

	public void setDestPath(String destPath) {
		this.destPath = destPath;
	}
}
