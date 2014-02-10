package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the UserBookmark database table.
 * 
 */
@Entity
@NamedQuery(name="UserBookmark.findAll", query="SELECT u FROM UserBookmark u")
public class UserBookmark implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String bookmarkPages;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	private String modifiedDate;

	//bi-directional many-to-one association to Book
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="guid")
	private Book book;

	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private User user;

	public UserBookmark() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookmarkPages() {
		return this.bookmarkPages;
	}

	public void setBookmarkPages(String bookmarkPages) {
		this.bookmarkPages = bookmarkPages;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(String modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}