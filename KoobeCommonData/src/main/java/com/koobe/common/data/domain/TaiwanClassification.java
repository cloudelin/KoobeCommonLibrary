package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TaiwanClassification database table.
 * 
 */
@Entity
@NamedQuery(name="TaiwanClassification.findAll", query="SELECT t FROM TaiwanClassification t")
public class TaiwanClassification implements Serializable, com.google.gwt.user.client.rpc.IsSerializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TaiwanClassificationPK id;

	private String name;

	public TaiwanClassification() {
	}

	public TaiwanClassificationPK getId() {
		return this.id;
	}

	public void setId(TaiwanClassificationPK id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}