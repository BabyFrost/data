package com.docteurfrost.data.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
@Table(name="T_PANIERS")
public class Panier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@OneToMany(mappedBy = "panier", cascade=CascadeType.ALL)
	@JsonManagedReference(value="panier_vente")
	private List<Vente> ventes = new ArrayList<>();
	
	@Column(name="DATE_CREATION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreation;
	
	@Column(name="NOMBRE_ARTICLES")
	private int nombreArticles;
	
	@Column(name="MONTANT_TOTAL")
	private int montantTotal;
	
	public Panier() { }
	
	public Panier( Date date ) {
		this.dateCreation = date;
	}

	public int getId() {
		return id;
	}

	public List<Vente> getVentes() {
		return ventes;
	}

	public void setVentes(List<Vente> ventes) {
		this.ventes = ventes;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	public int getNombreArticles() {
		return nombreArticles;
	}

	public void setNombreArticles(int nombreArticles) {
		this.nombreArticles = nombreArticles;
	}

	public int getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(int montantTotal) {
		this.montantTotal = montantTotal;
	}
	
}
