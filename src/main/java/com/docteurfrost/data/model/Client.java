package com.docteurfrost.data.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_CLIENTS")
public class Client {
	
	@Column(name="NOM")
	private String nom;
	
	@Column(name="PRENOM")
	private String prenom;
	
	@Column(name="DATE_NAISSANCE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDeNaissance;
	
	@Column(name="CNI")
	private int numeroCNI;
	
	@Column(name="EMAIL")
	private String email;
	
	@Id
	@NotNull
	@Pattern(regexp="[6]{1}[5,7,8,9]{1}[\\d]{7}")
	@Column(name="TELEPHONE")
	private String telephone;
	
	@Pattern(regexp="[F,M]{1}")
	@Column(name="SEXE")
	private String sexe;
	
	@Column(name="DATE_CREATION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	
	@OneToMany(mappedBy = "client", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="operations_client")
	private Collection<Operation> operations = new ArrayList<>();
	
	public Client() { }

	public Client( String nom, String prenom, Date dateDeNaissance, int numeroCNI, String email, String telephone, String sexe ) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.numeroCNI = numeroCNI;
		this.email = email;
		this.telephone = telephone;
		this.sexe = sexe;
		this.dateCreation = new Date();
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

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	
}
