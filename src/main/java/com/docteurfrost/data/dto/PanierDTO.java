package com.docteurfrost.data.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.docteurfrost.data.model.Panier;
import com.docteurfrost.data.model.Vente;

public class PanierDTO {
	
	private int id;
	private Date dateCreation;
	private ClientDTO client;
	private VendeurDTO vendeur;
	private List<ArticleDTO> articles = new ArrayList<>();
	private int nombreArticles;
	private int montantTotal;
	
	public PanierDTO() { }

	public PanierDTO( Panier panier) {
		this.id = panier.getId();
		this.dateCreation = panier.getDateCreation();
		
		ArticleDTO articleDTO;
		List<Vente> ventes =  panier.getVentes();
		
		this.client = new ClientDTO( ventes.get(0).getClient() );
		this.vendeur = new VendeurDTO( ventes.get(0).getVendeur() );
		
		this.montantTotal = 0;
		for ( int i=0; i < ventes.size(); i++ ) {
			articleDTO = new ArticleDTO( ventes.get(i).getArticle() );
			this.montantTotal += articleDTO.getPrix();
			articles.add(articleDTO);
		}
		
		this.nombreArticles = articles.size();
	}

	public int getId() {
		return id;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public List<ArticleDTO> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleDTO> articles) {
		this.articles = articles;
	}

	public ClientDTO getClient() {
		return client;
	}

	public void setClient(ClientDTO client) {
		this.client = client;
	}

	public VendeurDTO getVendeur() {
		return vendeur;
	}

	public void setVendeur(VendeurDTO vendeur) {
		this.vendeur = vendeur;
	}

	public int getNombreArticles() {
		return nombreArticles;
	}

	public void setNombreArticles(int nombreArticles) {
		this.nombreArticles = nombreArticles;
	}

	public int getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(int montantTotal) {
		this.montantTotal = montantTotal;
	}
	
}
