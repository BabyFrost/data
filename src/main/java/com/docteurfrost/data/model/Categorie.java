package com.docteurfrost.data.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.docteurfrost.data.model.article.Article;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_CATEGORIES")
public class Categorie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="NOM")
	private String nom;
	
	@Column(name="LIBELLE")
	private String libelle;
	
	@ManyToOne
	@JsonBackReference(value="categories_de_la_marque")
	@JoinColumn(name="MARQUE")
	private Marque marque;
	
	@OneToMany(mappedBy = "categorie", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER )
	@JsonManagedReference(value="article_categorie")
	private Collection<Article> articles = new ArrayList<>();
	
	@OneToMany(mappedBy = "categorie", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="categorie_options")
	private Collection<OptionCategorie> options = new ArrayList<>();
	
	public Categorie() { }

	public Categorie(String nom, String libelle ) {
		this.nom = nom;
		this.libelle = libelle;
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

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Collection<Article> getArticles() {
		return articles;
	}

	public void setArticles(Collection<Article> articles) {
		this.articles = articles;
	}

	public Collection<OptionCategorie> getOptions() {
		return options;
	}

	public void setOptions(Collection<OptionCategorie> options) {
		this.options = options;
	}
	
	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}
	
}