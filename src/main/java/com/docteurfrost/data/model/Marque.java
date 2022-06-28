package com.docteurfrost.data.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.model.categorie.Categorie;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_MARQUES")
public class Marque {
	
	@Id
	@Column(name="NOM")
	private String nom;
	
	@Column(name="LIBELLE")
	private String libelle;
	
	@ManyToMany(  cascade=CascadeType.REMOVE )
	@JoinTable( name = "T_MARQUE_CATEGORIE", joinColumns = @JoinColumn(name = "MARQUE"), inverseJoinColumns = @JoinColumn(name = "CATEGORIE") )
	@JsonManagedReference(value="categories_de_la_marque")
	private Collection<Categorie> categories = new ArrayList<>();
	
	@OneToMany(mappedBy = "marque", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="marque_article")
	private Collection<Article> articles = new ArrayList<>();
	
	public Marque() { }
	
	public Marque( String nom, String libelle) {
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

	public Collection<Categorie> getCategories() {
		return categories;
	}

	public void setCategories(Collection<Categorie> categories) {
		this.categories = categories;
	}

	public Collection<Article> getArticles() {
		return articles;
	}

	public void setArticles(Collection<Article> articles) {
		this.articles = articles;
	}
	
}
