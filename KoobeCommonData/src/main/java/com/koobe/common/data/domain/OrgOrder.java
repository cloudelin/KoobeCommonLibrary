package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the OrgOrder database table.
 * 
 */
@Entity
@NamedQuery(name="OrgOrder.findAll", query="SELECT o FROM OrgOrder o")
public class OrgOrder implements Serializable, com.google.gwt.user.client.rpc.IsSerializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrgOrderPK id;

	private Boolean available;

	private Integer copies;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

//	//bi-directional many-to-one association to Book
//	@ManyToOne(optional=false, fetch=FetchType.LAZY)
//	@JoinColumn(name="guid", referencedColumnName="guid")
//	private Book book;

//	//bi-directional many-to-one association to Org
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="orgId")
//	private Org org;

	public OrgOrder() {
	}

	public OrgOrderPK getId() {
		return this.id;
	}

	public void setId(OrgOrderPK id) {
		this.id = id;
	}

	public Boolean getAvailable() {
		return this.available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Integer getCopies() {
		return this.copies;
	}

	public void setCopies(Integer copies) {
		this.copies = copies;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

//	public Book getBook() {
//		return this.book;
//	}
//
//	public void setBook(Book book) {
//		this.book = book;
//	}

//	public Org getOrg() {
//		return this.org;
//	}
//
//	public void setOrg(Org org) {
//		this.org = org;
//	}

}