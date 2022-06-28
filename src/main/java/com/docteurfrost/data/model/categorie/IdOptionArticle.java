package com.docteurfrost.data.model.categorie;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Embeddable
public class IdOptionArticle implements Serializable {

	@Column(name = "ID_ARTICLE")
	private String idArticle;
	
	@Column(name = "ID_OPTION")
	private String idOption;
	
	public IdOptionArticle( ) { }

	public IdOptionArticle( String idArticle, String idOption) {
		this.idArticle = idArticle;
		this.idOption = idOption;
	}

}
