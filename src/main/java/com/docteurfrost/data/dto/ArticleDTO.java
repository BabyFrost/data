package com.docteurfrost.data.dto;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.docteurfrost.data.model.OptionArticle;
import com.docteurfrost.data.model.article.Article; 

public class ArticleDTO { 

//	private int id;
	private String nom;
	private String libelle;
	private String categorie;
	private int conteneur;
	private int prixAchat;
	private int prix;
	private MultipartFile file;
	private String marque;
	private String etat;
	private StringBuilder options;
	private String photo;
	private Date dateAjout;
	private Date dateMiseEnVente;
	private Boolean vip;

	public ArticleDTO() { }
	
	public ArticleDTO( Article article) {
//		this.id = article.getId();
		this.nom = article.getNom();
		this.libelle = article.getLibelle();
		this.categorie = article.getCategorie().getNom();
		this.conteneur = article.getConteneur().getId();
		this.prixAchat = article.getPrixAchat();
		this.prix = article.getPrix();
		if ( !(article.getMarque() == null) ) {
			this.marque = article.getMarque().getNom();
		}	
		this.etat = article.getState().toString();
		this.photo = article.getPhoto();
		this.dateAjout = article.getDateAjout();
		this.dateMiseEnVente = article.getDateAjout();
		this.vip = article.getVip();
		
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

	public int getConteneur() {
		return conteneur;
	}

	public void setConteneur(int conteneur) {
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

	public String getOptions() {
		return options.toString();
	}

	public void setOptions(String options) {
		this.options = new StringBuilder(options);
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public Date getDateAjout() {
		return dateAjout;
	}

	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}

	public Date getDateMiseEnVente() {
		return dateMiseEnVente;
	}

	public void setDateMiseEnVente(Date dateMiseEnVente) {
		this.dateMiseEnVente = dateMiseEnVente;
	}

	public Boolean getVip() {
		return vip;
	}

	public void setVip(Boolean vip) {
		this.vip = vip;
	}
	
}
