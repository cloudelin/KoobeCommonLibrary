package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the Manager database table.
 * 
 */
@Entity
@NamedQuery(name="Manager.findAll", query="SELECT m FROM Manager m")
public class Manager implements Serializable, com.google.gwt.user.client.rpc.IsSerializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Integer right;

	private Integer role;

	private String userId;

	public Manager() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRight() {
		return this.right;
	}

	public void setRight(Integer right) {
		this.right = right;
	}

	public Integer getRole() {
		return this.role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}