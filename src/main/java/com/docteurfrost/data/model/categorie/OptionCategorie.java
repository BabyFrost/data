package com.docteurfrost.data.model.categorie;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@Column(name="IS_NUMERIQUE")
	private Boolean isNumerique;
	
	@Column(name="IS_FREE")
	private Boolean isFree;
	
	@Column(name="IS_BOOLEAN")
	private Boolean isBoolean;
	
	@OneToMany(mappedBy = "option", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="article_options_categorie")
	private Set<OptionArticle> options = new HashSet<>();
	
	@OneToMany(mappedBy = "option", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="valeur_option")
	private Set<ValeurOption> valeurs = new HashSet<>();
	
	public OptionCategorie() { }

	public OptionCategorie(Categorie categorie, String nom, String libelle, Boolean isNumerique, Boolean isFree, Boolean isBoolean) {
		this.categorie = categorie;
		this.nom = nom.toUpperCase();
		this.id = this.nom.toUpperCase()+"_"+this.categorie.getNom();
		this.libelle = libelle;
		this.isNumerique = isNumerique;
		this.isFree = isFree;
		this.isBoolean = isBoolean;
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

	public Boolean getIsNumerique() {
		return isNumerique;
	}

	public void setIsNumerique(Boolean isNumerique) {
		this.isNumerique = isNumerique;
	}

	public Boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}

	public Boolean getIsBoolean() {
		return isBoolean;
	}

	public void setIsBoolean(Boolean isBoolean) {
		this.isBoolean = isBoolean;
	}

	public Set<ValeurOption> getValeurs() {
		return valeurs;
	}

	public void setValeurs(Set<ValeurOption> valeurs) {
		this.valeurs = valeurs;
	}

	public Set<OptionArticle> getOptions() {
		return options;
	}

	public void setOptions(Set<OptionArticle> options) {
		this.options = options;
	}
	
}
