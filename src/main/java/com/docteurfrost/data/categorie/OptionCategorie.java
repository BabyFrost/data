package com.docteurfrost.data.categorie;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_OPTIONS_CATEGORIE")
public class OptionCategorie {
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="NOM")
	private String nom;
	
	@ManyToOne
	@JsonBackReference(value="categorie_options")
	@JoinColumn(name="CATEGORIE")
	private Categorie categorie;
	
	@Column(name="LIBELLE")
	private String libelle;
	
	@Column(name="NUMERIQUE")
	private Boolean numerique;
	
	@OneToMany(mappedBy = "option", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="article_options_categorie")
	private Set<OptionArticle> options = new HashSet<>();
	
	@OneToMany(mappedBy = "option", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="valeur_option")
	private Set<ValeurOption> valeurs = new HashSet<>();
	
	public OptionCategorie() { }

	public OptionCategorie(Categorie categorie, String nom, String libelle, Boolean numerique) {
		this.id = nom+"_"+categorie.getNom();
		this.categorie = categorie;
		this.nom = nom;
		this.libelle = libelle;
		this.numerique = numerique;
	}

	public String getId() {
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
		this.nom = nom.toUpperCase();
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Boolean getNumerique() {
		return numerique;
	}

	public void setNumerique(Boolean numerique) {
		this.numerique = numerique;
	}

	public Set<ValeurOption> getValeurs() {
		return valeurs;
	}

	public void setValeurs(Set<ValeurOption> valeurs) {
		this.valeurs = valeurs;
	}
	
}
