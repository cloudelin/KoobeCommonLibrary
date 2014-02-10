package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the FacebookCommentsOnBook database table.
 * 
 */
@Entity
@NamedQuery(name="FacebookCommentsOnBook.findAll", query="SELECT f FROM FacebookCommentsOnBook f")
public class FacebookCommentsOnBook implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String commentId;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	private String href;

	//bi-directional many-to-one association to Book
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="guid")
	private Book book;

	public FacebookCommentsOnBook() {
	}

	public String getCommentId() {
		return this.commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getHref() {
		return this.href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}