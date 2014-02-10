package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the HostNameOrgMapping database table.
 * 
 */
@Entity
@NamedQuery(name="HostNameOrgMapping.findAll", query="SELECT h FROM HostNameOrgMapping h")
public class HostNameOrgMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	private String hostname;

	//bi-directional many-to-one association to Org
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="orgId")
	private Org org;

	public HostNameOrgMapping() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHostname() {
		return this.hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Org getOrg() {
		return this.org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

}