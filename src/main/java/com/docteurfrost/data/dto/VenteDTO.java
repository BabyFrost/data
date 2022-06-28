package com.docteurfrost.data.dto;

import java.util.Date;

import com.docteurfrost.data.model.Vente;

public class VenteDTO {
	
	private int id;
	private int panier;
	private String libelle;
	private Date date;
	private ClientDTO client;
	private ArticleDTO article;
	private VendeurDTO vendeur;
	private boolean isRetourne;
	
	public VenteDTO() { }

	public VenteDTO( Vente vente ) {
		this.id = vente.getId();
		this.panier = vente.getPanier().getId();
		this.libelle = vente.getLibelle();
		this.date = vente.getDate();
		this.client = new ClientDTO( vente.getClient() );
		this.article = new ArticleDTO( vente.getArticle() );
		this.vendeur = new VendeurDTO ( vente.getVendeur() );
		this.isRetourne = vente.isRetourne();
	}

	public int getPanier() {
		return panier;
	}

	public void setPanier(int panier) {
		this.panier = panier;
	}

	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public boolean isRetourne() {
		return isRetourne;
	}

	public void setRetourne(boolean isRetourne) {
		this.isRetourne = isRetourne;
	}

}
