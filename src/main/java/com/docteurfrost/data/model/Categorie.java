package com.docteurfrost.data.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name="T_CATEGORIE")
public class Categorie {

	@Id
	@Column(name="NOM")
	private String nom;
	
	@Column(name="LIBELLE")
	private String libelle;
	
	@JsonIdentityInfo(
			  generator = ObjectIdGenerators.PropertyGenerator.class, 
			  property = "id")
	@OneToMany(mappedBy = "categorie", cascade=CascadeType.REMOVE)
	@JsonManagedReference
	private Collection<Article> articles = new ArrayList<>();
	
	@OneToMany(mappedBy = "categorie", cascade=CascadeType.REMOVE)
	@JsonManagedReference
	private Collection<Option> options = new ArrayList<>();
	
	public Categorie() { }

	public Categorie(String nom, String libelle ) {
		this.nom = nom;
		this.libelle = libelle;
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

	public Collection<Option> getOptions() {
		return options;
	}

	public void setOptions(Collection<Option> options) {
		this.options = options;
	}
	
}