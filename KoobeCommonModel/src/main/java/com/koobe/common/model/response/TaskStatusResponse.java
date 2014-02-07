package com.koobe.common.model.response;

/**
 * Response model for restful service of querying the task status
 * @author cloude
 * @since 2014-1-7
 */
public class TaskStatusResponse {
	
	private String taskId;

	private Integer totalProgress;
	
	private Integer currProgress;
	
	private Integer percentCompleted;
	
	private String status;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public Integer getTotalProgress() {
		return totalProgress;
	}

	public void setTotalProgress(Integer totalProgress) {
		this.totalProgress = totalProgress;
	}

	public Integer getCurrProgress() {
		return currProgress;
	}

	public void setCurrProgress(Integer currProgress) {
		this.currProgress = currProgress;
	}

	public Integer getPercentCompleted() {
		return percentCompleted;
	}

	public void setPercentCompleted(Integer percentCompleted) {
		this.percentCompleted = percentCompleted;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
