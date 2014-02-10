package com.koobe.common.data.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the FAQ database table.
 * 
 */
@Entity
@NamedQuery(name="Faq.findAll", query="SELECT f FROM Faq f")
public class Faq implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Lob
	private String answer;

	private String question;

	public Faq() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

}