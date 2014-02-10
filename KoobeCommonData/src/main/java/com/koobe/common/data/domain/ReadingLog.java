package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ReadingLog database table.
 * 
 */
@Entity
@NamedQuery(name="ReadingLog.findAll", query="SELECT r FROM ReadingLog r")
public class ReadingLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Integer accumulatedElapsed;

	private Integer action;

	private String bookGuid;

	private Integer elapsed;

	private String remark;

	private String sessionId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	private String userAgent;

	private String userId;

	private String value;

	public ReadingLog() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAccumulatedElapsed() {
		return this.accumulatedElapsed;
	}

	public void setAccumulatedElapsed(Integer accumulatedElapsed) {
		this.accumulatedElapsed = accumulatedElapsed;
	}

	public Integer getAction() {
		return this.action;
	}

	public void setAction(Integer action) {
		this.action = action;
	}

	public String getBookGuid() {
		return this.bookGuid;
	}

	public void setBookGuid(String bookGuid) {
		this.bookGuid = bookGuid;
	}

	public Integer getElapsed() {
		return this.elapsed;
	}

	public void setElapsed(Integer elapsed) {
		this.elapsed = elapsed;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}