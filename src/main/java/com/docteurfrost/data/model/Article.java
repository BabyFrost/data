package com.docteurfrost.data.model;

import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name="T_ARTICLE")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="NOM")
	private String nom;
	
	@Column(name="LIBELLE")
	private String libelle;
	
	@JsonIdentityInfo(
			  generator = ObjectIdGenerators.PropertyGenerator.class, 
			  property = "id")
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="CATEGORIE")
	private Categorie categorie;
	
	@Column(name="PRIX_ACHAT")
	private int prixAchat;
	
	@Column(name="PRIX")
	private int prix;

	@Column(name="TAGS")
	String tagsss;

	public Article() { }
	
	public Article( String nom, String libelle, Categorie categorie, int prixAchat, int prix) {
		this.nom = nom;
		this.libelle = libelle;
		this.categorie = categorie;
		this.prixAchat = prixAchat;
		this.prix = prix;
		HashMap<String, String> tags = new HashMap<>();
		tags.put("Telecommande", "Oui");
		tags.put("Satelitte", "Non");
		tags.put("Pieds", "Non");
		tags.put("Couleur", "Noir");
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			tagsss = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(tags);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			
		}
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
	
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public int getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(int prixAchat) {
		this.prixAchat = prixAchat;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public String getTagsss() {
		return tagsss;
	}

	public void setTagsss(String tagsss) {
		this.tagsss = tagsss;
	}	
	
}
