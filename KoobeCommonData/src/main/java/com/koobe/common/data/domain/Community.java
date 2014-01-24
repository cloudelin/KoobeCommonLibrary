package com.koobe.common.data.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the Community database table.
 * 
 */
@Entity
@NamedQuery(name="Community.findAll", query="SELECT c FROM Community c")
public class Community implements Serializable, com.google.gwt.user.client.rpc.IsSerializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String name;

	private Integer type;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="community")
	private List<User> users;

	public Community() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setCommunity(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setCommunity(null);

		return user;
	}

}