package com.docteurfrost.data.dto;

import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.docteurfrost.data.categorie.OptionArticle;
import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.tools.DateStringConverter; 

public class ArticleDTO { 

	private String nom;
	private String observation;
	private String numeroDeSerie;
	private String categorie;
	private int conteneur;
	private String marque;
	private int prixAchat;
	private int prixLiquidation;
	private int prixEstimatif;
	private int prix;
	private MultipartFile file;
	private String status;
	private StringBuilder options;
	private String photo;
	private String dateAchat;
	private String dateSaisie;
	private String dateMiseEnVente;
//	private Boolean vip;
	private String etat;

	public ArticleDTO() { }
	
	public ArticleDTO( Article article) {
		this.nom = article.getNom();
		this.observation = article.getObservation();
		this.numeroDeSerie = article.getNumeroDeSerie();
		this.categorie = article.getCategorie().getNom();
		this.conteneur = article.getConteneur().getId();
		this.prixAchat = article.getPrixAchat();
		this.prixLiquidation = article.getPrixLiquidation();
		this.prixEstimatif = article.getPrixEstimatif();
		this.prix = article.getPrix();
		this.marque = article.getMarque().getNom();
		this.status = article.getState().toString();
		this.photo = article.getPhoto();
		this.dateAchat = DateStringConverter.dateToString( article.getDateAchat() );
		this.dateSaisie = DateStringConverter.dateToString( article.getDateSaisie() ) ;
		this.dateMiseEnVente = DateStringConverter.dateToString( article.getDateSaisie() );
//		this.vip = article.getVip();
		this.etat = article.getEtat();
		
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

	public String getObservation() {
		return observation;
	}
	
	public void setObservation(String observation) {
		this.observation = observation;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(String dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public String getDateMiseEnVente() {
		return dateMiseEnVente;
	}

	public void setDateMiseEnVente(String dateMiseEnVente) {
		this.dateMiseEnVente = dateMiseEnVente;
	}

//	public Boolean getVip() {
//		return vip;
//	}
//
//	public void setVip(Boolean vip) {
//		this.vip = vip;
//	}

	public int getPrixLiquidation() {
		return prixLiquidation;
	}

	public void setPrixLiquidation(int prixLiquidation) {
		this.prixLiquidation = prixLiquidation;
	}

	public int getPrixEstimatif() {
		return prixEstimatif;
	}

	public void setPrixEstimatif(int prixEstimatif) {
		this.prixEstimatif = prixEstimatif;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(String dateAchat) {
		this.dateAchat = dateAchat;
	}
	
}
