package com.docteurfrost.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class IdOptionArticle implements Serializable {
	
	private static final long serialVersionUID = 1L;

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
