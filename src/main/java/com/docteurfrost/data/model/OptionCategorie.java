package com.docteurfrost.data.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_OPTIONS_CATEGORIE")
public class OptionCategorie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="NOM")
	private String nom;
	
	@ManyToOne
	@JsonBackReference(value="categorie_options")
	@JoinColumn(name="CATEGORIE")
	private Categorie categorie;
	
	@Column(name="LIBELLE")
	private String libelle;
	
	@OneToMany(mappedBy = "option", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="article_options_categorie")
	private Set<OptionArticle> options = new HashSet<>();
	
	public OptionCategorie() { }

	public OptionCategorie(Categorie categorie, String nom, String libelle) {
		this.categorie = categorie;
		this.nom = nom;
		this.libelle = libelle;
	}

	public int getId() {
		return id;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
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
	
}
