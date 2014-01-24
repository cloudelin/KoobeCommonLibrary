package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the OrgValidIP database table.
 * 
 */
@Embeddable
public class OrgValidIPPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int orgId;

	private String nmap;

	public OrgValidIPPK() {
	}
	public int getOrgId() {
		return this.orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getNmap() {
		return this.nmap;
	}
	public void setNmap(String nmap) {
		this.nmap = nmap;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrgValidIPPK)) {
			return false;
		}
		OrgValidIPPK castOther = (OrgValidIPPK)other;
		return 
			(this.orgId == castOther.orgId)
			&& this.nmap.equals(castOther.nmap);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orgId;
		hash = hash * prime + this.nmap.hashCode();
		
		return hash;
	}
}