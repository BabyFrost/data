package com.docteurfrost.data.dto;

import java.text.ParseException;
import java.util.Date;

import com.docteurfrost.data.model.Retour;
import com.docteurfrost.data.tools.DateStringConverter;

public class RetourDTO {
	
	private int id;
	private String libelle;
	private String date;
	private ClientDTO client;
	private ArticleDTO article;
	private VendeurDTO vendeur;
	private VenteDTO vente;
	
	public RetourDTO() { }

	public RetourDTO( Retour retour ) {
		this.id = retour.getId();
		this.libelle = retour.getLibelle();
		this.date = DateStringConverter.dateToString( retour.getDate() );
		this.client = new ClientDTO( retour.getVente().getClient() );
		this.article = new ArticleDTO( retour.getVente().getArticle() );
		this.vendeur = new VendeurDTO( retour.getVendeur() );
		this.vente = new VenteDTO( retour.getVente() );
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Date getDate() throws ParseException {
		return DateStringConverter.stringToDate( this.date );
	}

	public void setDate(Date date) {
		this.date = DateStringConverter.dateToString( date );
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public ArticleDTO getArticle() {
		return article;
	}

	public void setArticle(ArticleDTO article) {
		this.article = article;
	}

	public VendeurDTO getVendeur() {
		return vendeur;
	}

	public void setVendeur(VendeurDTO vendeur) {
		this.vendeur = vendeur;
	}

	public VenteDTO getVente() {
		return vente;
	}

	public void setVente(VenteDTO vente) {
		this.vente = vente;
	}

}
