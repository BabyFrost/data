package com.docteurfrost.data.dto.interne;

import javax.validation.constraints.NotNull;

import com.docteurfrost.data.model.article.Article;

public class ArticleInterneDTO { 

	
	private String id;
	@NotNull
	private String marque;
	private int prix;
	private String photo;

	public ArticleInterneDTO() { }
	
	public ArticleInterneDTO( Article article) {
		this.id = article.getId();
		this.prix = article.getPrix();
		this.marque = article.getMarque().getNom();
		this.photo = article.getPhoto();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

}
