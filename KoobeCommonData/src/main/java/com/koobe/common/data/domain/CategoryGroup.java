package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the CategoryGroup database table.
 * 
 */
@Entity
@NamedQuery(name="CategoryGroup.findAll", query="SELECT c FROM CategoryGroup c")
public class CategoryGroup implements Serializable, com.google.gwt.user.client.rpc.IsSerializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String categoryName;

	private String classifiedNo;

	private String groupName;

	public CategoryGroup() {
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