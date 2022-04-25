package com.docteurfrost.data.dto;

import com.docteurfrost.data.model.Conteneur;

public class ConteneurDTO {
	
	private int id;
	private String nom;
	
	public ConteneurDTO() { }
	
	public ConteneurDTO( Conteneur conteneur) {
		this.id = conteneur.getId();
		this.nom = conteneur.getNom();
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
 
}
