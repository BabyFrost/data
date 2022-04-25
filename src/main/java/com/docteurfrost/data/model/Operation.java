package com.docteurfrost.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.docteurfrost.data.model.article.Article;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS)
//@Table(name="T_OPERATIONS")
//@Inheritance
//@DiscriminatorColumn(name="CUSTOMER_TYPE")
//@DiscriminatorValue("INDIVIDUAL")
public abstract class Operation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="ID")
	private int id;
	
	@Column(name="LIBELLE")
	private String libelle;
	
	@Column(name="DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	@ManyToOne
	@JsonBackReference(value="operations_client")
	@JoinColumn(name="CLIENT")
	private Client client;
	
	@ManyToOne
	@JsonBackReference(value="operations_article")
	@JoinColumn(name="ARTICLE")
	private Article article;
	
	@ManyToOne
	@JsonBackReference(value="operations_utilisateur")
	@JoinColumn(name="VENDEUR")
	private Utilisateur vendeur;
	
	public Operation() { }

	public Operation( String libelle, Client client, Article article, Utilisateur vendeur ) {
		this.libelle = libelle;
		this.date = new Date();
		this.client = client;
		this.article = article;
		this.vendeur = vendeur;
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

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

}
