package com.docteurfrost.data.dto.interne;

import java.util.Date;

import com.docteurfrost.data.dto.ArticleDTO;
import com.docteurfrost.data.dto.ClientDTO;
import com.docteurfrost.data.dto.VendeurDTO;
import com.docteurfrost.data.dto.VenteDTO;
import com.docteurfrost.data.model.Retour;

public class RetourInterneDTO {
	
	private int id;
	private String libelle;
	private Date date;
	private ClientDTO client;
	private ArticleDTO article;
	private VendeurDTO vendeur;
	private VenteDTO vente;
	
	public RetourInterneDTO() { }

	public RetourInterneDTO( Retour retour ) {
		this.id = retour.getId();
		this.libelle = retour.getLibelle();
		this.date = retour.getDate();
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

	public VenteDTO getVente() {
		return vente;
	}

	public void setVente(VenteDTO vente) {
		this.vente = vente;
	}

}
