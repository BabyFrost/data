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
	private int idArticle;
	
	@Column(name = "ID_OPTION")
	private int idOption;
	
	public IdOptionArticle( ) { }

	public IdOptionArticle( int idArticle, int idOption) {
		this.idArticle = idArticle;
		this.idOption = idOption;
	}

}
