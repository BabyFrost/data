package com.docteurfrost.data.dto.interne;

import java.util.ArrayList;

import javax.validation.constraints.NotNull;

import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.model.categorie.OptionArticle;

public class ArticleInterneDTO { 

	
	private String id;
	private String nom;
	@NotNull
	private String marque;
	private int prix;
	private int prixAchat;
	private String photo;
	private StringBuilder options;
	private String numeroDeSerie;
	private String categorie;
	private String observation;

	public ArticleInterneDTO() { }
	
	public ArticleInterneDTO( Article article) {
		this.id = article.getId();
		this.nom = article.getNom().toUpperCase();
		this.prix = article.getPrix();
		this.prixAchat = article.getPrixAchat();
		this.marque = article.getMarque().getNom();
		this.photo = article.getPhoto();
		this.numeroDeSerie = article.getNumeroDeSerie();
		this.categorie = article.getCategorie().getNom();
		this.observation = article.getObservation();
		
		options = new StringBuilder();
		ArrayList<OptionArticle> optionsArticle = new ArrayList<>( article.getOptions() );
		OptionArticle optionArticle;
		for ( int i=0; i<optionsArticle.size(); i++) {
			optionArticle = optionsArticle.get(i);
			options.append( "{" );
				options.append( "" + optionArticle.getOption().getNom() + ":" );
				options.append( "" + optionArticle.getValeur() + "" );
			options.append( "}," );
		}
		if ( options.length() > 0 ) {
			options.deleteCharAt(options.length()-1);
		}
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom.toUpperCase();
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public int getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(int prixAchat) {
		this.prixAchat = prixAchat;
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
	
	public String getOptions() {
		
		if ( this.options == null ) {
			return null;
		} else {
			return options.toString();	
		}
		
	}

	public void setOptions(String options) {
		this.options = new StringBuilder(options);
	}

	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	
	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
	
	public String getObservation() {
		return observation;
	}
	
	public void setObservation(String observation) {
		this.observation = observation;
	}

}
