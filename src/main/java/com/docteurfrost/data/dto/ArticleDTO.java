package com.docteurfrost.data.dto;

import java.util.ArrayList;

import com.docteurfrost.data.model.OptionArticle;
import com.docteurfrost.data.model.article.Article; 

public class ArticleDTO { 

	private int id;
	private String nom;
	private String libelle;
	private String categorie;
	private String conteneur;
	private int prixAchat;
	private int prix;
	private String marque;
	private String etat;
	private StringBuilder options;
//	private List<OptionArticleDTO> options;

	public ArticleDTO() { }
	
	public ArticleDTO( Article article) {
		this.id = article.getId();
		this.nom = article.getNom();
		this.libelle = article.getLibelle();
		this.categorie = article.getCategorie().getNom();
		this.conteneur = article.getConteneur().getNom();
		this.prixAchat = article.getPrixAchat();
		this.prix = article.getPrix();
		if ( !(article.getMarque() == null) ) {
			this.marque = article.getMarque().getNom();
		}	
		this.etat = article.getState().toString();
		
		options = new StringBuilder();
//		options.append("[");
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
	
//		options.append( "]" );
		
//		options = new ArrayList<>();
//		OptionArticleDTO optionArticleDTO;
//		List<OptionArticle> optionsArticle;
//		optionsArticle =  new ArrayList<>( article.getOptions() );
//		for ( int i=0; i < optionsArticle.size(); i++ ) {
//			optionArticleDTO = new OptionArticleDTO( optionsArticle.get(i) );
//			options.add(optionArticleDTO);
//		}
		
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	
	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getConteneur() {
		return conteneur;
	}

	public void setConteneur(String conteneur) {
		this.conteneur = conteneur;
	}

	public int getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(int prixAchat) {
		this.prixAchat = prixAchat;
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

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

//	public List<OptionArticleDTO> getOptions() {
//		return options;
//	}
//
//	public void setOptions(List<OptionArticleDTO> options) {
//		this.options = options;
//	}

	public String getOptions() {
		return options.toString();
	}

	public void setOptions(String options) {
		this.options = new StringBuilder(options);
	}
	
}
