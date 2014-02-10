package com.koobe.common.data.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Book database table.
 * 
 */
@Entity
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false)
	private String guid;

	private String bucket;

	private Integer coverHeight;

	private String coverHref;

	private String coverId;

	private Integer coverWidth;

	private Boolean disabled;

	private String flipOrder;

	private String folder;

	private String koobeLayout;

	private String kt;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyDate;

	private String name;

	private String opfRootPath;

	private Integer zipped;

	//bi-directional many-to-one association to BookChannelItem
	@OneToMany(mappedBy="book")
	private List<BookChannelItem> bookChannelItems;

	//bi-directional one-to-one association to BookSpineContent
	@OneToOne(mappedBy="book", fetch=FetchType.LAZY)
	private BookSpineContent bookSpineContent;

	//bi-directional one-to-one association to BookTocContent
	@OneToOne(mappedBy="book", fetch=FetchType.LAZY)
	private BookTocContent bookTocContent;

	//bi-directional many-to-one association to FacebookCommentsOnBook
	@OneToMany(mappedBy="book")
	private List<FacebookCommentsOnBook> facebookCommentsOnBooks;

	//bi-directional many-to-one association to FacebookLikeOnBook
	@OneToMany(mappedBy="book")
	private List<FacebookLikeOnBook> facebookLikeOnBooks;

//	//bi-directional many-to-one association to OrgOrder
//	@OneToMany(mappedBy="book")
//	private List<OrgOrder> orgOrders;

	//bi-directional many-to-one association to UserBookmark
	@OneToMany(mappedBy="book")
	private List<UserBookmark> userBookmarks;

	public Book() {
	}

	public String getGuid() {
		return this.guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getBucket() {
		return this.bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public Integer getCoverHeight() {
		return this.coverHeight;
	}

	public void setCoverHeight(Integer coverHeight) {
		this.coverHeight = coverHeight;
	}

	public String getCoverHref() {
		return this.coverHref;
	}

	public void setCoverHref(String coverHref) {
		this.coverHref = coverHref;
	}

	public String getCoverId() {
		return this.coverId;
	}

	public void setCoverId(String coverId) {
		this.coverId = coverId;
	}

	public Integer getCoverWidth() {
		return this.coverWidth;
	}

	public void setCoverWidth(Integer coverWidth) {
		this.coverWidth = coverWidth;
	}

	public Boolean getDisabled() {
		return this.disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public String getFlipOrder() {
		return this.flipOrder;
	}

	public void setFlipOrder(String flipOrder) {
		this.flipOrder = flipOrder;
	}

	public String getFolder() {
		return this.folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getKoobeLayout() {
		return this.koobeLayout;
	}

	public void setKoobeLayout(String koobeLayout) {
		this.koobeLayout = koobeLayout;
	}

	public String getKt() {
		return this.kt;
	}

	public void setKt(String kt) {
		this.kt = kt;
	}

	public Date getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOpfRootPath() {
		return this.opfRootPath;
	}

	public void setOpfRootPath(String opfRootPath) {
		this.opfRootPath = opfRootPath;
	}

	public Integer getZipped() {
		return this.zipped;
	}

	public void setZipped(Integer zipped) {
		this.zipped = zipped;
	}

	public List<BookChannelItem> getBookChannelItems() {
		return this.bookChannelItems;
	}

	public void setBookChannelItems(List<BookChannelItem> bookChannelItems) {
		this.bookChannelItems = bookChannelItems;
	}

	public BookChannelItem addBookChannelItem(BookChannelItem bookChannelItem) {
		getBookChannelItems().add(bookChannelItem);
		bookChannelItem.setBook(this);

		return bookChannelItem;
	}

	public BookChannelItem removeBookChannelItem(BookChannelItem bookChannelItem) {
		getBookChannelItems().remove(bookChannelItem);
		bookChannelItem.setBook(null);

		return bookChannelItem;
	}

	public BookSpineContent getBookSpineContent() {
		return this.bookSpineContent;
	}

	public void setBookSpineContent(BookSpineContent bookSpineContent) {
		this.bookSpineContent = bookSpineContent;
	}

	public BookTocContent getBookTocContent() {
		return this.bookTocContent;
	}

	public void setBookTocContent(BookTocContent bookTocContent) {
		this.bookTocContent = bookTocContent;
	}

	public List<FacebookCommentsOnBook> getFacebookCommentsOnBooks() {
		return this.facebookCommentsOnBooks;
	}

	public void setFacebookCommentsOnBooks(List<FacebookCommentsOnBook> facebookCommentsOnBooks) {
		this.facebookCommentsOnBooks = facebookCommentsOnBooks;
	}

	public FacebookCommentsOnBook addFacebookCommentsOnBook(FacebookCommentsOnBook facebookCommentsOnBook) {
		getFacebookCommentsOnBooks().add(facebookCommentsOnBook);
		facebookCommentsOnBook.setBook(this);

		return facebookCommentsOnBook;
	}

	public FacebookCommentsOnBook removeFacebookCommentsOnBook(FacebookCommentsOnBook facebookCommentsOnBook) {
		getFacebookCommentsOnBooks().remove(facebookCommentsOnBook);
		facebookCommentsOnBook.setBook(null);

		return facebookCommentsOnBook;
	}

	public List<FacebookLikeOnBook> getFacebookLikeOnBooks() {
		return this.facebookLikeOnBooks;
	}

	public void setFacebookLikeOnBooks(List<FacebookLikeOnBook> facebookLikeOnBooks) {
		this.facebookLikeOnBooks = facebookLikeOnBooks;
	}

	public FacebookLikeOnBook addFacebookLikeOnBook(FacebookLikeOnBook facebookLikeOnBook) {
		getFacebookLikeOnBooks().add(facebookLikeOnBook);
		facebookLikeOnBook.setBook(this);

		return facebookLikeOnBook;
	}

	public FacebookLikeOnBook removeFacebookLikeOnBook(FacebookLikeOnBook facebookLikeOnBook) {
		getFacebookLikeOnBooks().remove(facebookLikeOnBook);
		facebookLikeOnBook.setBook(null);

		return facebookLikeOnBook;
	}

//	public List<OrgOrder> getOrgOrders() {
//		return this.orgOrders;
//	}
//
//	public void setOrgOrders(List<OrgOrder> orgOrders) {
//		this.orgOrders = orgOrders;
//	}
//
//	public OrgOrder addOrgOrder(OrgOrder orgOrder) {
//		getOrgOrders().add(orgOrder);
//		orgOrder.setBook(this);
//
//		return orgOrder;
//	}
//
//	public OrgOrder removeOrgOrder(OrgOrder orgOrder) {
//		getOrgOrders().remove(orgOrder);
//		orgOrder.setBook(null);
//
//		return orgOrder;
//	}

	public List<UserBookmark> getUserBookmarks() {
		return this.userBookmarks;
	}

	public void setUserBookmarks(List<UserBookmark> userBookmarks) {
		this.userBookmarks = userBookmarks;
	}

	public UserBookmark addUserBookmark(UserBookmark userBookmark) {
		getUserBookmarks().add(userBookmark);
		userBookmark.setBook(this);

		return userBookmark;
	}

	public UserBookmark removeUserBookmark(UserBookmark userBookmark) {
		getUserBookmarks().remove(userBookmark);
		userBookmark.setBook(null);

		return userBookmark;
	}

}