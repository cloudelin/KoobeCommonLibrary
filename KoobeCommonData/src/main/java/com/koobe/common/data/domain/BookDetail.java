package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the BookDetail database table.
 * 
 */
@Entity
@NamedQuery(name="BookDetail.findAll", query="SELECT b FROM BookDetail b")
public class BookDetail implements Serializable, com.google.gwt.user.client.rpc.IsSerializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String guid;

	private Integer adult;

	private String author;

	private String authorPicture;

	@Column(name="class")
	private String class_;

	private String classifiedNo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Lob
	private String description;

	private String displayName;

	private Integer displayPublishDate;

	private String ean;

	private String editor;

	private String isbn;

	private String koobeClassifiedNo;

	private String koobeMainClass;

	private String koobeSubClass;

	private String lang;

	private Double listPrice;

	private String listPriceUnit;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	private String name;

	private Integer pages;

	private Boolean publicRight;

	@Temporal(TemporalType.TIMESTAMP)
	private Date publishDate;

	private String publisher;

	@Lob
	private String remark;

	private String seriesName;

	private String subClass;

	private String translator;

	private Integer volume;

	private String volumeTitle;

	public BookDetail() {
	}

	public String getGuid() {
		return this.guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public Integer getAdult() {
		return this.adult;
	}

	public void setAdult(Integer adult) {
		this.adult = adult;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAuthorPicture() {
		return this.authorPicture;
	}

	public void setAuthorPicture(String authorPicture) {
		this.authorPicture = authorPicture;
	}

	public String getClass_() {
		return this.class_;
	}

	public void setClass_(String class_) {
		this.class_ = class_;
	}

	public String getClassifiedNo() {
		return this.classifiedNo;
	}

	public void setClassifiedNo(String classifiedNo) {
		this.classifiedNo = classifiedNo;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Integer getDisplayPublishDate() {
		return this.displayPublishDate;
	}

	public void setDisplayPublishDate(Integer displayPublishDate) {
		this.displayPublishDate = displayPublishDate;
	}

	public String getEan() {
		return this.ean;
	}

	public void setEan(String ean) {
		this.ean = ean;
	}

	public String getEditor() {
		return this.editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getKoobeClassifiedNo() {
		return this.koobeClassifiedNo;
	}

	public void setKoobeClassifiedNo(String koobeClassifiedNo) {
		this.koobeClassifiedNo = koobeClassifiedNo;
	}

	public String getKoobeMainClass() {
		return this.koobeMainClass;
	}

	public void setKoobeMainClass(String koobeMainClass) {
		this.koobeMainClass = koobeMainClass;
	}

	public String getKoobeSubClass() {
		return this.koobeSubClass;
	}

	public void setKoobeSubClass(String koobeSubClass) {
		this.koobeSubClass = koobeSubClass;
	}

	public String getLang() {
		return this.lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Double getListPrice() {
		return this.listPrice;
	}

	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}

	public String getListPriceUnit() {
		return this.listPriceUnit;
	}

	public void setListPriceUnit(String listPriceUnit) {
		this.listPriceUnit = listPriceUnit;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPages() {
		return this.pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Boolean getPublicRight() {
		return this.publicRight;
	}

	public void setPublicRight(Boolean publicRight) {
		this.publicRight = publicRight;
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

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSeriesName() {
		return this.seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getSubClass() {
		return this.subClass;
	}

	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}

	public String getTranslator() {
		return this.translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public Integer getVolume() {
		return this.volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public String getVolumeTitle() {
		return this.volumeTitle;
	}

	public void setVolumeTitle(String volumeTitle) {
		this.volumeTitle = volumeTitle;
	}

}