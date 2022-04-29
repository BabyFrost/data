package com.docteurfrost.data.dto;

import java.util.Date;

import com.docteurfrost.data.model.Conteneur;

public class ConteneurDTO {
	
	private int id;
	private String nom;
	private String pays;
	private Date dateDepart;
	private Date dateArrivee;
	private Date dateDechargement;
	
	public ConteneurDTO() { }
	
	public ConteneurDTO( Conteneur conteneur) {
		this.id = conteneur.getId();
		this.nom = conteneur.getNom();
		this.pays = conteneur.getPays();
		this.dateDepart = conteneur.getDepart();
		this.dateArrivee = conteneur.getArrivee();
		this.dateDechargement = conteneur.getDechargement();
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

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public Date getDepart() {
		return dateDepart;
	}

	public void setDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	public Date getArrivee() {
		return dateArrivee;
	}

	public void setArrivee(Date arrivee) {
		this.dateArrivee = arrivee;
	}

	public Date getDechargement() {
		return dateDechargement;
	}

	public void setDechargement(Date dechargement) {
		this.dateDechargement = dechargement;
	}
 
}
