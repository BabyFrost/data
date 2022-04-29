package com.docteurfrost.data.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_CLIENTS")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="NOM")
	private String nom;
	
	@Column(name="PRENOM")
	private String prenom;
	
	@Column(name="DATE_DE_NAISSANCE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateDeNaissance;
	
	@Column(name="CNI")
	private int numeroCNI;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="TELEPHONE")
	private int telephone;
	
	@OneToMany(mappedBy = "client", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="operations_client")
	private Collection<Operation> operations = new ArrayList<>();
	
	public Client() { }

	public Client( String nom, String prenom, Date dateDeNaissance, int numeroCNI, String email, int telephone ) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.numeroCNI = numeroCNI;
		this.email = email;
		this.telephone = telephone;
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