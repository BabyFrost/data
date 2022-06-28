package com.docteurfrost.data.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.docteurfrost.data.dto.interne.ArticleInterneDTO;
import com.docteurfrost.data.dto.interne.ClientInterneDTO;
import com.docteurfrost.data.dto.interne.VendeurInterneDTO;
import com.docteurfrost.data.model.Panier;
import com.docteurfrost.data.model.Vente;

public class PanierDTO {
	
	private int id;
	private Date dateCreation;
	private ClientInterneDTO client;
	private VendeurInterneDTO vendeur;
	@NotNull
	private List<ArticleInterneDTO> articles = new ArrayList<>();
	private int nombreArticles;
	private int montantTotal;
	
	public PanierDTO() { }

	public PanierDTO( Panier panier) {
		this.id = panier.getId();
		this.dateCreation = panier.getDateCreation();
		
		ArticleInterneDTO articleDTO;
		List<Vente> ventes =  panier.getVentes();
		
		this.client = new ClientInterneDTO( ventes.get(0).getClient() );
		this.vendeur = new VendeurInterneDTO( ventes.get(0).getVendeur() );
		
		this.montantTotal = 0;
		for ( int i=0; i < ventes.size(); i++ ) {
			articleDTO = new ArticleInterneDTO( ventes.get(i).getArticle() );
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

	public ClientInterneDTO getClient() {
		return client;
	}

	public void setClient(ClientInterneDTO client) {
		this.client = client;
	}

	public VendeurInterneDTO getVendeur() {
		return vendeur;
	}

	public void setVendeur(VendeurInterneDTO vendeur) {
		this.vendeur = vendeur;
	}

	public List<ArticleInterneDTO> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleInterneDTO> articles) {
		this.articles = articles;
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
