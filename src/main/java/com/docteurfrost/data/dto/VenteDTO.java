package com.docteurfrost.data.dto;

import com.docteurfrost.data.dto.interne.ArticleInterneDTO;
import com.docteurfrost.data.dto.interne.ClientInterneDTO;
import com.docteurfrost.data.dto.interne.VendeurInterneDTO;
import com.docteurfrost.data.model.Vente;
import com.docteurfrost.data.tools.DateStringConverter;

public class VenteDTO {
	
	private int id;
	private int panier;
	private String libelle;
	private String date;
	private ClientInterneDTO client;
	private ArticleInterneDTO article;
	private VendeurInterneDTO vendeur;
	private boolean isRetourne;
	
	public VenteDTO() { }

	public VenteDTO( Vente vente ) {
		this.id = vente.getId();
		this.panier = vente.getPanier().getId();
		this.libelle = vente.getLibelle();
		this.date = DateStringConverter.dateToString( vente.getDate() );
		this.client = new ClientInterneDTO( vente.getClient() );
		this.article = new ArticleInterneDTO( vente.getArticle() );
		this.vendeur = new VendeurInterneDTO ( vente.getVendeur() );
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public ClientInterneDTO getClient() {
		return client;
	}

	public void setClient(ClientInterneDTO client) {
		this.client = client;
	}

	public ArticleInterneDTO getArticle() {
		return article;
	}

	public void setArticle(ArticleInterneDTO article) {
		this.article = article;
	}

	public VendeurInterneDTO getVendeur() {
		return vendeur;
	}

	public void setVendeur(VendeurInterneDTO vendeur) {
		this.vendeur = vendeur;
	}

	public boolean getisRetourne() {
		return isRetourne;
	}

	public void setisRetourne(boolean isRetourne) {
		this.isRetourne = isRetourne;
	}

}
