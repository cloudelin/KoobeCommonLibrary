package com.koobe.common.model.message;

import com.koobe.common.model.enums.FileTypeEnum;

/**
 * 
 * @author cloude
 * @since 2014-1-22
 */
public class ConvertEpubRequest extends GeneralRequest {
	
	public final static String ACTION = "SendConvertEpubRequest";
	
	private String srcS3bucket;
	
	private String destS3bucket;
	
	private FileTypeEnum srcFileType;
	
	private FileTypeEnum destFileType;
	
	private String srcPath;
	
	private String destPath;

	public ConvertEpubRequest() {
		super(ACTION);
	}

	public ConvertEpubRequest(String srcS3bucket, String destS3bucket, FileTypeEnum srcFileType,
			FileTypeEnum destFileType, String srcPath, String destPath) {
		super(ACTION);
		this.srcS3bucket = srcS3bucket;
		this.destS3bucket = destS3bucket;
		this.srcFileType = srcFileType;
		this.destFileType = destFileType;
		this.srcPath = srcPath;
		this.destPath = destPath;
	}

	public String getSrcS3bucket() {
		return srcS3bucket;
	}

	public void setSrcS3bucket(String srcS3bucket) {
		this.srcS3bucket = srcS3bucket;
	}

	public String getDestS3bucket() {
		return destS3bucket;
	}

	public void setDestS3bucket(String destS3bucket) {
		this.destS3bucket = destS3bucket;
	}

	public FileTypeEnum getSrcFileType() {
		return srcFileType;
	}

	public void setSrcFileType(FileTypeEnum srcFileType) {
		this.srcFileType = srcFileType;
	}

	public FileTypeEnum getDestFileType() {
		return destFileType;
	}

	public void setDestFileType(FileTypeEnum destFileType) {
		this.destFileType = destFileType;
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
