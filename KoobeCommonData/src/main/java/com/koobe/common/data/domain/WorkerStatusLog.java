package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the WorkerStatusLog database table.
 * 
 */
@Entity
public class WorkerStatusLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Lob
	private String errorMessage;

	private Integer finalProgress;

	private Boolean hasError;

	private String messageId;

	private String status;

	private Integer totalProgress;

	private String workerHostIp;

	public WorkerStatusLog() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getFinalProgress() {
		return this.finalProgress;
	}

	public void setFinalProgress(Integer finalProgress) {
		this.finalProgress = finalProgress;
	}

	public Boolean getHasError() {
		return this.hasError;
	}

	public void setHasError(Boolean hasError) {
		this.hasError = hasError;
	}

	public String getMessageId() {
		return this.messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTotalProgress() {
		return this.totalProgress;
	}

	public void setTotalProgress(Integer totalProgress) {
		this.totalProgress = totalProgress;
	}

	public String getWorkerHostIp() {
		return this.workerHostIp;
	}

	public void setWorkerHostIp(String workerHostIp) {
		this.workerHostIp = workerHostIp;
	}

}