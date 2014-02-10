package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the BookChannel database table.
 * 
 */
@Entity
@NamedQuery(name="BookChannel.findAll", query="SELECT b FROM BookChannel b")
public class BookChannel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String category;

	private String description;

	private String image;

	private String language;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastBuildDate;

	private String link;

	@Temporal(TemporalType.TIMESTAMP)
	private Date pubDate;

	private Integer rating;

	private Integer skipDays;

	private Integer skipHours;

	private String title;

	private Integer ttl;

	private String webMaster;

	//bi-directional many-to-one association to BookChannelItem
	@OneToMany(mappedBy="bookChannel")
	private List<BookChannelItem> bookChannelItems;

	public BookChannel() {
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

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLanguage() {
		return this.language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getLastBuildDate() {
		return this.lastBuildDate;
	}

	public void setLastBuildDate(Date lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
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

	public Integer getRating() {
		return this.rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getSkipDays() {
		return this.skipDays;
	}

	public void setSkipDays(Integer skipDays) {
		this.skipDays = skipDays;
	}

	public Integer getSkipHours() {
		return this.skipHours;
	}

	public void setSkipHours(Integer skipHours) {
		this.skipHours = skipHours;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getTtl() {
		return this.ttl;
	}

	public void setTtl(Integer ttl) {
		this.ttl = ttl;
	}

	public String getWebMaster() {
		return this.webMaster;
	}

	public void setWebMaster(String webMaster) {
		this.webMaster = webMaster;
	}

	public List<BookChannelItem> getBookChannelItems() {
		return this.bookChannelItems;
	}

	public void setBookChannelItems(List<BookChannelItem> bookChannelItems) {
		this.bookChannelItems = bookChannelItems;
	}

	public BookChannelItem addBookChannelItem(BookChannelItem bookChannelItem) {
		getBookChannelItems().add(bookChannelItem);
		bookChannelItem.setBookChannel(this);

		return bookChannelItem;
	}

	public BookChannelItem removeBookChannelItem(BookChannelItem bookChannelItem) {
		getBookChannelItems().remove(bookChannelItem);
		bookChannelItem.setBookChannel(null);

		return bookChannelItem;
	}

}