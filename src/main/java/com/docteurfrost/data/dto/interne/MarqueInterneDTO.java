package com.docteurfrost.data.dto.interne;

import com.docteurfrost.data.model.Marque;

public class MarqueInterneDTO {
	
	private String nom;
	private String libelle;
	
	public MarqueInterneDTO() { }
	
	public MarqueInterneDTO( Marque marque) {
		this.nom = marque.getNom();
		this.libelle = marque.getLibelle();
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
