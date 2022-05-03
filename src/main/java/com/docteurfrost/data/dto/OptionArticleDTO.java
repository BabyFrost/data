package com.docteurfrost.data.dto;

import com.docteurfrost.data.categorie.OptionArticle;


public class OptionArticleDTO {

	private String nom;
	private String valeur;
	
	public OptionArticleDTO( ) { }
	
	public OptionArticleDTO( OptionArticle optionArticle ) {
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
