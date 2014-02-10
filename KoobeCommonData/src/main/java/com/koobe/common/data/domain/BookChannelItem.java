package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the BookChannelItems database table.
 * 
 */
@Entity
@Table(name="BookChannelItems")
@NamedQuery(name="BookChannelItem.findAll", query="SELECT b FROM BookChannelItem b")
public class BookChannelItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String category;

	private String description;

	private String link;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pubDate;

	private String title;

	//bi-directional many-to-one association to Book
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="guid")
	private Book book;

	//bi-directional many-to-one association to BookChannel
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idChannel")
	private BookChannel bookChannel;

	public BookChannelItem() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Date getPubDate() {
		return this.pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public BookChannel getBookChannel() {
		return this.bookChannel;
	}

	public void setBookChannel(BookChannel bookChannel) {
		this.bookChannel = bookChannel;
	}

}