package com.docteurfrost.data.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.docteurfrost.data.model.article.Article;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_CONTENEURS")
public class Conteneur {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="NOM")
	private String nom;
	
	@Column(name="PAYS")
	private String pays;
	
	@Column(name="DEPART")
	private Date depart;
	
	@Column(name="ARRIVEE")
	private Date arrivee;
	
	@Column(name="DECHARGEMENT")
	private Date dechargement;
	
	@OneToMany(mappedBy = "conteneur", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="conteneur_article")
	private Collection<Article> articles = new ArrayList<>();
	
	public Conteneur() { }
	
	public Conteneur( int id, String nom, String pays, Date depart, Date arrivee, Date dechargement) {
		this.id = id;
		this.nom = nom;
		this.pays = pays;
		this.depart = depart;
		this.arrivee = arrivee;
		this.dechargement = dechargement;
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

	public Collection<Article> getArticles() {
		return articles;
	}

	public void setArticles(Collection<Article> articles) {
		this.articles = articles;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public Date getDepart() {
		return depart;
	}

	public void setDepart(Date depart) {
		this.depart = depart;
	}

	public Date getArrivee() {
		return arrivee;
	}

	public void setArrivee(Date arrivee) {
		this.arrivee = arrivee;
	}

	public Date getDechargement() {
		return dechargement;
	}

	public void setDechargement(Date dechargement) {
		this.dechargement = dechargement;
	}
	
}
