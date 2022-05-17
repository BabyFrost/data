package com.docteurfrost.data.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.docteurfrost.data.model.Client;
import com.docteurfrost.data.tools.DateStringConverter;

public class ClientDTO {
	
	private String nom;
	private String prenom;
	private String dateDeNaissance;
	private int numeroCNI;
	private String email;
	@NotNull
	@Pattern(regexp="[6]{1}[5,7,8,9]{1}[\\d]{7}", message = "Renseignez un format valide de numero de telephone")
	private String telephone;
	private String sexe;
	private String dateCreation;
	
	public ClientDTO() { }

	public ClientDTO( Client client ) {
		this.nom = client.getNom();
		this.prenom = client.getPrenom();
		this.dateDeNaissance = DateStringConverter.dateToString( client.getDateDeNaissance() );
		this.numeroCNI = client.getNumeroCNI();
		this.email = client.getEmail();
		this.telephone = client.getTelephone();
		this.sexe = client.getSexe();
		this.dateCreation = DateStringConverter.dateToString( client.getDateCreation() );
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

	public String getDateDeNaissance() {
		return dateDeNaissance;
	}

	public void setDateDeNaissance(String dateDeNaissance) {
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}

}
