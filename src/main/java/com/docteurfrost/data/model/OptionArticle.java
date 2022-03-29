package com.docteurfrost.data.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="T_OPTIONARTICLE")
public class OptionArticle {
	
	@EmbeddedId 
	@Column(name="ID")
	private IdOptionArticle id;
	
	@ManyToOne
    @MapsId("idArticle")
    @JoinColumn(name = "ARTICLE")
	@JsonBackReference
	private Article article;
	
	@ManyToOne
	@MapsId("idOption")
	@JoinColumn(name="OPTION")
	@JsonBackReference
	private Option option;
	
	public OptionArticle( ) { }
	
	public OptionArticle(IdOptionArticle id, Article article, Option option ) {
		this.id = id;
		this.article = article;
		this.option = option;
	}
	
}
