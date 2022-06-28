package com.docteurfrost.data.dto.interne;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.docteurfrost.data.model.Client;

public class ClientInterneDTO {
	
	private String nom;
	private String prenom;
	@NotNull
	@Pattern(regexp="[6]{1}[5,7,8,9]{1}[\\d]{7}", message = "Renseignez un format valide de numero de telephone")
	private String telephone;
	private String sexe;
	
	public ClientInterneDTO() { }

	public ClientInterneDTO( Client client ) {
		this.nom = client.getNom();
		this.prenom = client.getPrenom();
		this.telephone = client.getTelephone();
		this.sexe = client.getSexe();
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

}
