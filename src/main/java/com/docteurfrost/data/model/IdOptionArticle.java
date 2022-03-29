package com.docteurfrost.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class IdOptionArticle implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "ID_ETUDIANT")
	private int idArticle;
	
	@Column(name = "ID_LISTE")
	private int idOption;
	
	public IdOptionArticle( ) { }

	public IdOptionArticle( int idArticle, int idOption) {
		this.idArticle = idArticle;
		this.idOption = idOption;
	}

}
