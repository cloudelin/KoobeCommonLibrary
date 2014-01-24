package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the BookSpineContent database table.
 * 
 */
@Entity
@NamedQuery(name="BookSpineContent.findAll", query="SELECT b FROM BookSpineContent b")
public class BookSpineContent implements Serializable, com.google.gwt.user.client.rpc.IsSerializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String guid;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Lob
	private String spine;

	@Lob
	private String tree;

	//bi-directional one-to-one association to Book
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="guid")
	private Book book;

	public BookSpineContent() {
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

	public String getSpine() {
		return this.spine;
	}

	public void setSpine(String spine) {
		this.spine = spine;
	}

	public String getTree() {
		return this.tree;
	}

	public void setTree(String tree) {
		this.tree = tree;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}