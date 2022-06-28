package com.docteurfrost.data.dto.interne;

import com.docteurfrost.data.model.Utilisateur;

public class VendeurInterneDTO {
	
	private int id;
	private String nom;
	private String prenom;
	private String sexe;

	public VendeurInterneDTO() { }

	public VendeurInterneDTO( Utilisateur utilisateur ) {
		this.id = utilisateur.getId();
		this.nom = utilisateur.getNom();
		this.prenom = utilisateur.getPrenom();
		this.sexe = utilisateur.getSexe();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

}
