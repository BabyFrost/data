package com.docteurfrost.data.dto.interne;

import com.docteurfrost.data.model.categorie.OptionArticle;


public class OptionArticleInterneDTO {

	private String nom;
	private String valeur;
	
	public OptionArticleInterneDTO( ) { }
	
	public OptionArticleInterneDTO( OptionArticle optionArticle ) {
		this.nom = optionArticle.getOption().getNom();
		this.valeur = optionArticle.getValeur();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
}
