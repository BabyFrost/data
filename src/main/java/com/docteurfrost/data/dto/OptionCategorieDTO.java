package com.docteurfrost.data.dto;

import com.docteurfrost.data.model.OptionCategorie;

public class OptionCategorieDTO {

	private int id;
	private String nom;
	private String libelle;
	
	public OptionCategorieDTO() { }

	public OptionCategorieDTO( OptionCategorie optionCategorie ) {
//		this.id = optionCategorie.getId();
		this.nom = optionCategorie.getNom();
		this.libelle = optionCategorie.getLibelle();
	}

//	public int getId() {
//		return id;
//	}

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
