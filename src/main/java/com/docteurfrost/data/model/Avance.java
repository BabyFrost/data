package com.docteurfrost.data.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.docteurfrost.data.model.reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="T_AVANCES")
public class Avance {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@ManyToOne
	@JsonBackReference(value="avances_reservation")
	@JoinColumn(name="RESERVATION")
	private Reservation reservation;
	
	@ManyToOne
	@JsonBackReference(value="avance_vendeur")
	@JoinColumn(name="VENDEUR")
	private Utilisateur vendeur;
	
	@Column(name="MONTANT")
	private int montant;
	
	@Column(name="DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	public Avance() {
		this.date = new Date(); 
	}
	
	public Avance( Reservation reservation, Utilisateur vendeur, int montant ) {
		this.reservation = reservation;
		this.vendeur = vendeur;
		this.montant = montant;
		this.date = new Date();
	}

	public int getId() {
		return id;
	}

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Utilisateur getVendeur() {
		return vendeur;
	}

	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}

}
