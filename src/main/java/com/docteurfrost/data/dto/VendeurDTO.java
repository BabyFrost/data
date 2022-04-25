package com.docteurfrost.data.dto;

import java.util.Date;

import com.docteurfrost.data.model.Utilisateur;

public class VendeurDTO {
	
	private int id;
	private String nom;
	private String prenom;
	private Date dateDeNaissance;
	private int numeroCNI;
	private String email;
	private int telephone;

	public VendeurDTO() { }

	public VendeurDTO( Utilisateur utilisateur ) {
		this.id = utilisateur.getId();
		this.nom = utilisateur.getNom();
		this.prenom = utilisateur.getPrenom();
		this.dateDeNaissance = utilisateur.getDateDeNaissance();
		this.numeroCNI = utilisateur.getNumeroCNI();
		this.email = utilisateur.getEmail();
		this.telephone = utilisateur.getTelephone();
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

	public Date getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(Date dateDeNaissance) {
		this.dateDeNaissance = dateDeNaissance;
	}

	public int getNumeroCNI() {
		return numeroCNI;
	}

	public void setNumeroCNI(int numeroCNI) {
		this.numeroCNI = numeroCNI;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

}
