package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the DraftDetail database table.
 * 
 */
@Entity
@NamedQuery(name="DraftDetail.findAll", query="SELECT d FROM DraftDetail d")
public class DraftDetail implements Serializable, com.google.gwt.user.client.rpc.IsSerializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String author;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date publishDate;

	private String publisher;

	//bi-directional many-to-one association to Draft
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="draftId")
	private Draft draft;

	public DraftDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getPublisher() {
		return this.publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Draft getDraft() {
		return this.draft;
	}

	public void setDraft(Draft draft) {
		this.draft = draft;
	}

}