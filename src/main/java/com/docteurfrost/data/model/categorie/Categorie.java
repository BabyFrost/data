package com.docteurfrost.data.model.categorie;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.docteurfrost.data.model.Marque;
import com.docteurfrost.data.model.article.Article;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_CATEGORIES")
public class Categorie {
	
	@Id
	@Column(name="NOM")
	private String nom;
	
	@Column(name="LIBELLE")
	private String libelle;
	
	@ManyToMany(mappedBy="categories")
	@JsonBackReference(value="categories_de_la_marque")
	Collection<Marque> marques = new ArrayList<>();
	
	@OneToMany(mappedBy = "categorie", cascade=CascadeType.REMOVE, fetch=FetchType.EAGER )
	@JsonManagedReference(value="article_categorie")
	private Collection<Article> articles = new ArrayList<>();
	
	@OneToMany(mappedBy = "categorie", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="categorie_options")
	private Collection<OptionCategorie> options = new ArrayList<>();
	
	public Categorie() { }

	public Categorie(String nom, String libelle ) {
		this.nom = nom.toUpperCase();
		this.libelle = libelle;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom.toUpperCase();
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

	public Collection<Marque> getMarques() {
		return marques;
	}

	public void setMarques(Collection<Marque> marques) {
		this.marques = marques;
	}
	
}