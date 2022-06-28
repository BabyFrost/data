package com.docteurfrost.data.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_UTILISATEURS")
public class Utilisateur {
	
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
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="MOT_DE_PASSE")
	private String password;
	
	@Pattern(regexp="[F,M]{1}")
	@Column(name="SEXE")
	private String sexe;
	
	@OneToMany(mappedBy = "vendeur", cascade=CascadeType.ALL)
	@JsonManagedReference(value="operations_utilisateur")
	private Collection<Operation> operations = new ArrayList<>();
	
	@ManyToOne
	@JsonBackReference(value="utilisateur_role")
	@JoinColumn(name="ROLE")
	private Role role;
	
	@OneToMany(mappedBy = "vendeur", cascade=CascadeType.ALL)
	@JsonManagedReference(value="avance_vendeur")
	private List<Avance> avances = new ArrayList<>();
	
	public Utilisateur() { }

	public Utilisateur( String nom, String prenom, Date dateDeNaissance, int numeroCNI, String email,
			int telephone, String username, String password, String sexe) {
		this.nom = nom;
		this.prenom = prenom;
		this.dateDeNaissance = dateDeNaissance;
		this.numeroCNI = numeroCNI;
		this.email = email;
		this.telephone = telephone;
		this.username = username;
		this.password = password;
		this.sexe = sexe;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

}
