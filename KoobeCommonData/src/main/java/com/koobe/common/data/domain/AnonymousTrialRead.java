package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the AnonymousTrialRead database table.
 * 
 */
@Entity
@NamedQuery(name="AnonymousTrialRead.findAll", query="SELECT a FROM AnonymousTrialRead a")
public class AnonymousTrialRead implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String browserId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@Lob
	private String trialStatus;

	private String userAgent;

	public AnonymousTrialRead() {
	}

	public String getBrowserId() {
		return this.browserId;
	}

	public void setBrowserId(String browserId) {
		this.browserId = browserId;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getTrialStatus() {
		return this.trialStatus;
	}

	public void setTrialStatus(String trialStatus) {
		this.trialStatus = trialStatus;
	}

	public String getUserAgent() {
		return this.userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

}