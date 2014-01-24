package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TaiwanClassification database table.
 * 
 */
@Embeddable
public class TaiwanClassificationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private String mainClass;

	private String subClass;

	public TaiwanClassificationPK() {
	}
	public String getMainClass() {
		return this.mainClass;
	}
	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}
	public String getSubClass() {
		return this.subClass;
	}
	public void setSubClass(String subClass) {
		this.subClass = subClass;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TaiwanClassificationPK)) {
			return false;
		}
		TaiwanClassificationPK castOther = (TaiwanClassificationPK)other;
		return 
			this.mainClass.equals(castOther.mainClass)
			&& this.subClass.equals(castOther.subClass);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.mainClass.hashCode();
		hash = hash * prime + this.subClass.hashCode();
		
		return hash;
	}
}