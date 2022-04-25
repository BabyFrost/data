package com.docteurfrost.data.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.docteurfrost.data.model.article.Article;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_CONTENEURS")
public class Conteneur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="NOM")
	private String nom;
	
	@OneToMany(mappedBy = "conteneur", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="conteneur_article")
	private Collection<Article> articles = new ArrayList<>();
	
	public Conteneur() { }
	
	public int getId() {
		return id;
	}

	public Conteneur( String nom) {
		this.nom = nom;
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
	
}
