package com.koobe.common.data.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the Org database table.
 * 
 */
@Entity
@NamedQuery(name="Org.findAll", query="SELECT o FROM Org o")
public class Org implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private Boolean accountInfoEnabled;

	private Boolean allowApplyNewAccount;

	private Boolean allowChangePassword;

	private Boolean applyWithinIPs;

	private String authenticationUrl;

	private Boolean bookmarkEnabled;

	private Integer borrowLimit;

	private String colorScheme;

	private Integer copies;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private String desktopBackground;

	private String domainName;

	private Boolean flipModeEnabled;

	private Integer loginType;

	private Boolean loginWithinIPs;

	private String logo;

	private String name;

	private String remark;

	private Boolean shareEnabled;

	private Boolean showCategoryView;

	private Integer timeLimit;

	private Integer trialReadPercentage;

	private Integer type;

//	//bi-directional many-to-one association to HostNameOrgMapping
//	@OneToMany(mappedBy="org")
//	private List<HostNameOrgMapping> hostNameOrgMappings;

//	//bi-directional many-to-one association to OrgNew
//	@OneToMany(mappedBy="org")
//	private List<OrgNew> orgNews;

//	//bi-directional many-to-one association to OrgOrder
//	@OneToMany(mappedBy="org")
//	private List<OrgOrder> orgOrders;

//	//bi-directional many-to-one association to OrgValidIP
//	@OneToMany(mappedBy="org")
//	private List<OrgValidIP> orgValidIps;

//	//bi-directional many-to-one association to User
//	@OneToMany(mappedBy="org")
//	private List<User> users;

	public Org() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getAccountInfoEnabled() {
		return this.accountInfoEnabled;
	}

	public void setAccountInfoEnabled(Boolean accountInfoEnabled) {
		this.accountInfoEnabled = accountInfoEnabled;
	}

	public Boolean getAllowApplyNewAccount() {
		return this.allowApplyNewAccount;
	}

	public void setAllowApplyNewAccount(Boolean allowApplyNewAccount) {
		this.allowApplyNewAccount = allowApplyNewAccount;
	}

	public Boolean getAllowChangePassword() {
		return this.allowChangePassword;
	}

	public void setAllowChangePassword(Boolean allowChangePassword) {
		this.allowChangePassword = allowChangePassword;
	}

	public Boolean getApplyWithinIPs() {
		return this.applyWithinIPs;
	}

	public void setApplyWithinIPs(Boolean applyWithinIPs) {
		this.applyWithinIPs = applyWithinIPs;
	}

	public String getAuthenticationUrl() {
		return this.authenticationUrl;
	}

	public void setAuthenticationUrl(String authenticationUrl) {
		this.authenticationUrl = authenticationUrl;
	}

	public Boolean getBookmarkEnabled() {
		return this.bookmarkEnabled;
	}

	public void setBookmarkEnabled(Boolean bookmarkEnabled) {
		this.bookmarkEnabled = bookmarkEnabled;
	}

	public Integer getBorrowLimit() {
		return this.borrowLimit;
	}

	public void setBorrowLimit(Integer borrowLimit) {
		this.borrowLimit = borrowLimit;
	}

	public String getColorScheme() {
		return this.colorScheme;
	}

	public void setColorScheme(String colorScheme) {
		this.colorScheme = colorScheme;
	}

	public Integer getCopies() {
		return this.copies;
	}

	public void setCopies(Integer copies) {
		this.copies = copies;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDesktopBackground() {
		return this.desktopBackground;
	}

	public void setDesktopBackground(String desktopBackground) {
		this.desktopBackground = desktopBackground;
	}

	public String getDomainName() {
		return this.domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public Boolean getFlipModeEnabled() {
		return this.flipModeEnabled;
	}

	public void setFlipModeEnabled(Boolean flipModeEnabled) {
		this.flipModeEnabled = flipModeEnabled;
	}

	public Integer getLoginType() {
		return this.loginType;
	}

	public void setLoginType(Integer loginType) {
		this.loginType = loginType;
	}

	public Boolean getLoginWithinIPs() {
		return this.loginWithinIPs;
	}

	public void setLoginWithinIPs(Boolean loginWithinIPs) {
		this.loginWithinIPs = loginWithinIPs;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getShareEnabled() {
		return this.shareEnabled;
	}

	public void setShareEnabled(Boolean shareEnabled) {
		this.shareEnabled = shareEnabled;
	}

	public Boolean getShowCategoryView() {
		return this.showCategoryView;
	}

	public void setShowCategoryView(Boolean showCategoryView) {
		this.showCategoryView = showCategoryView;
	}

	public Integer getTimeLimit() {
		return this.timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getTrialReadPercentage() {
		return this.trialReadPercentage;
	}

	public void setTrialReadPercentage(Integer trialReadPercentage) {
		this.trialReadPercentage = trialReadPercentage;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

//	public List<HostNameOrgMapping> getHostNameOrgMappings() {
//		return this.hostNameOrgMappings;
//	}
//
//	public void setHostNameOrgMappings(List<HostNameOrgMapping> hostNameOrgMappings) {
//		this.hostNameOrgMappings = hostNameOrgMappings;
//	}
//
//	public HostNameOrgMapping addHostNameOrgMapping(HostNameOrgMapping hostNameOrgMapping) {
//		getHostNameOrgMappings().add(hostNameOrgMapping);
//		hostNameOrgMapping.setOrg(this);
//
//		return hostNameOrgMapping;
//	}
//
//	public HostNameOrgMapping removeHostNameOrgMapping(HostNameOrgMapping hostNameOrgMapping) {
//		getHostNameOrgMappings().remove(hostNameOrgMapping);
//		hostNameOrgMapping.setOrg(null);
//
//		return hostNameOrgMapping;
//	}

//	public List<OrgNew> getOrgNews() {
//		return this.orgNews;
//	}
//
//	public void setOrgNews(List<OrgNew> orgNews) {
//		this.orgNews = orgNews;
//	}

//	public OrgNew addOrgNew(OrgNew orgNew) {
//		getOrgNews().add(orgNew);
//		orgNew.setOrg(this);
//
//		return orgNew;
//	}
//
//	public OrgNew removeOrgNew(OrgNew orgNew) {
//		getOrgNews().remove(orgNew);
//		orgNew.setOrg(null);
//
//		return orgNew;
//	}

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
//		orgOrder.setOrg(this);
//
//		return orgOrder;
//	}
//
//	public OrgOrder removeOrgOrder(OrgOrder orgOrder) {
//		getOrgOrders().remove(orgOrder);
//		orgOrder.setOrg(null);
//
//		return orgOrder;
//	}

//	public List<OrgValidIP> getOrgValidIps() {
//		return this.orgValidIps;
//	}
//
//	public void setOrgValidIps(List<OrgValidIP> orgValidIps) {
//		this.orgValidIps = orgValidIps;
//	}
//
//	public OrgValidIP addOrgValidIp(OrgValidIP orgValidIp) {
//		getOrgValidIps().add(orgValidIp);
//		orgValidIp.setOrg(this);
//
//		return orgValidIp;
//	}
//
//	public OrgValidIP removeOrgValidIp(OrgValidIP orgValidIp) {
//		getOrgValidIps().remove(orgValidIp);
//		orgValidIp.setOrg(null);
//
//		return orgValidIp;
//	}

//	public List<User> getUsers() {
//		return this.users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}

//	public User addUser(User user) {
//		getUsers().add(user);
//		user.setOrg(this);
//
//		return user;
//	}
//
//	public User removeUser(User user) {
//		getUsers().remove(user);
//		user.setOrg(null);
//
//		return user;
//	}

}