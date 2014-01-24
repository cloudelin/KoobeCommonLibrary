package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the OrgOrder database table.
 * 
 */
@Embeddable
public class OrgOrderPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	//@Column(insertable=false, updatable=false, nullable=false)
	private Long orgId;

	//@Column(insertable=false, updatable=false, nullable=false)
	private String guid;

	public OrgOrderPK() {
	}
	public Long getOrgId() {
		return this.orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getGuid() {
		return this.guid;
	}
	public void setGuid(String guid) {
		this.guid = guid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof OrgOrderPK)) {
			return false;
		}
		OrgOrderPK castOther = (OrgOrderPK)other;
		return 
			(this.orgId == castOther.orgId)
			&& this.guid.equals(castOther.guid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orgId.intValue();
		hash = hash * prime + this.guid.hashCode();
		
		return hash;
	}
}