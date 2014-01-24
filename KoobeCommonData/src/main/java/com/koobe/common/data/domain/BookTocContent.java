package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the BookTocContent database table.
 * 
 */
@Entity
@NamedQuery(name="BookTocContent.findAll", query="SELECT b FROM BookTocContent b")
public class BookTocContent implements Serializable, com.google.gwt.user.client.rpc.IsSerializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String guid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Lob
	private String toc;

	//bi-directional one-to-one association to Book
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="guid")
	private Book book;

	public BookTocContent() {
	}

	public String getGuid() {
		return this.guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getToc() {
		return this.toc;
	}

	public void setToc(String toc) {
		this.toc = toc;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}