package com.docteurfrost.data.categorie;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.docteurfrost.data.model.article.Article;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="T_OPTIONS_ARTICLE")
public class OptionArticle {
	
	@EmbeddedId 
	@Column(name="ID")
	private IdOptionArticle id;
	
	@ManyToOne
    @MapsId("idArticle")
    @JoinColumn(name = "ARTICLE")
	@JsonBackReference(value="article_options_article")
	private Article article;
	
	@ManyToOne
	@MapsId("idOption")
	@JoinColumn(name="OPTION_CATEGORIE")
	@JsonBackReference(value="article_options_categorie")
	private OptionCategorie option;
	
	@Column(name="VALEUR")
	private String valeur;
	
	public OptionArticle( ) { }
	
	public OptionArticle( Article article, OptionCategorie option, String valeur ) {
		this.id = new IdOptionArticle( article.getNom(), option.getNom() );
		this.article = article;
		this.option = option;
		this.valeur = valeur;
	}

	public IdOptionArticle getId() {
		return id;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public OptionCategorie getOption() {
		return option;
	}

	public void setOption(OptionCategorie option) {
		this.option = option;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
}
