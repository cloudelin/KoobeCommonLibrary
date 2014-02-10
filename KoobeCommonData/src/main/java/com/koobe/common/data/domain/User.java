package com.koobe.common.data.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Boolean adminRole;

	private String affiliateUser;

	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;

	private String email;

	private Integer gender;

	private String name;

	private String password;

	private String remark;

	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	private String userId;

	private Boolean valid;
	
	//bi-directional many-to-many association to Draft
	@ManyToMany(mappedBy="users")
	private List<Draft> drafts;

	//bi-directional many-to-one association to Community
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="communityId")
	private Community community;

	//bi-directional many-to-one association to Org
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="orgId")
	private Org org;

	//bi-directional many-to-one association to UserBookmark
	@OneToMany(mappedBy="user")
	private List<UserBookmark> userBookmarks;

	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAdminRole() {
		return this.adminRole;
	}

	public void setAdminRole(Boolean adminRole) {
		this.adminRole = adminRole;
	}

	public String getAffiliateUser() {
		return this.affiliateUser;
	}

	public void setAffiliateUser(String affiliateUser) {
		this.affiliateUser = affiliateUser;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGender() {
		return this.gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Boolean getValid() {
		return this.valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	public List<Draft> getDrafts() {
		return this.drafts;
	}

	public void setDrafts(List<Draft> drafts) {
		this.drafts = drafts;
	}

	public Community getCommunity() {
		return this.community;
	}

	public void setCommunity(Community community) {
		this.community = community;
	}

	public Org getOrg() {
		return this.org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public List<UserBookmark> getUserBookmarks() {
		return this.userBookmarks;
	}

	public void setUserBookmarks(List<UserBookmark> userBookmarks) {
		this.userBookmarks = userBookmarks;
	}

	public UserBookmark addUserBookmark(UserBookmark userBookmark) {
		getUserBookmarks().add(userBookmark);
		userBookmark.setUser(this);

		return userBookmark;
	}

	public UserBookmark removeUserBookmark(UserBookmark userBookmark) {
		getUserBookmarks().remove(userBookmark);
		userBookmark.setUser(null);

		return userBookmark;
	}

}