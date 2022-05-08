package com.docteurfrost.data.dto;

import com.docteurfrost.data.conteneur.Conteneur;
import com.docteurfrost.data.tools.DateStringConverter;

public class ConteneurDTO {
	
	private int id;
	private String nom;
	private String pays;
	private String dateDepart;
	private String dateArrivee;
	private String dateDechargement;
	private String status;
	
	public ConteneurDTO() { }
	
	public ConteneurDTO( Conteneur conteneur) {
		this.id = conteneur.getId();
		this.nom = conteneur.getNom();
		this.pays = conteneur.getPays();
		this.dateDepart = DateStringConverter.dateToString( conteneur.getDateDepart() );
		this.dateArrivee = DateStringConverter.dateToString( conteneur.getDateArrivee() );
		this.dateDechargement = DateStringConverter.dateToString( conteneur.getDateDechargement() );
		this.status = conteneur.getState().toString();
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

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(String dateDepart) {
		this.dateDepart = dateDepart;
	}

	public String getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(String arrivee) {
		this.dateArrivee = arrivee;
	}

	public String getDateDechargement() {
		return dateDechargement;
	}

	public void setDateDechargement(String dechargement) {
		this.dateDechargement = dechargement;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
 
}
