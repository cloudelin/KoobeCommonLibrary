package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the KoobeCategoryGroup database table.
 * 
 */
@Entity
@NamedQuery(name="KoobeCategoryGroup.findAll", query="SELECT k FROM KoobeCategoryGroup k")
public class KoobeCategoryGroup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String categoryName;

	private String classifiedNo;

	private String groupName;

	public KoobeCategoryGroup() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getClassifiedNo() {
		return this.classifiedNo;
	}

	public void setClassifiedNo(String classifiedNo) {
		this.classifiedNo = classifiedNo;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}