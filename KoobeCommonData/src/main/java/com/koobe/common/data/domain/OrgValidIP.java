package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the OrgValidIP database table.
 * 
 */
@Entity
@NamedQuery(name="OrgValidIP.findAll", query="SELECT o FROM OrgValidIP o")
public class OrgValidIP implements Serializable, com.google.gwt.user.client.rpc.IsSerializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrgValidIPPK id;

//	//bi-directional many-to-one association to Org
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="orgId")
//	private Org org;

	public OrgValidIP() {
	}

	public OrgValidIPPK getId() {
		return this.id;
	}

	public void setId(OrgValidIPPK id) {
		this.id = id;
	}

//	public Org getOrg() {
//		return this.org;
//	}
//
//	public void setOrg(Org org) {
//		this.org = org;
//	}

}