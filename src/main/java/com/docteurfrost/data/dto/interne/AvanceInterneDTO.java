package com.docteurfrost.data.dto.interne;

import com.docteurfrost.data.model.Avance;
import com.docteurfrost.data.tools.DateStringConverter;

public class AvanceInterneDTO {

	private int id;
	private int reservation;
	private VendeurInterneDTO vendeur;
	private int montant;
	private String date;
	
	public AvanceInterneDTO () {}

	public AvanceInterneDTO ( Avance avance ) {
		
		this.id = avance.getId();
		this.reservation = avance.getReservation().getId();
		this.vendeur = new VendeurInterneDTO ( avance.getVendeur() );
		this.montant = avance.getMontant();
		this.date = DateStringConverter.dateToString( avance.getDate() );
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getReservation() {
		return reservation;
	}

	public void setReservation(int reservation) {
		this.reservation = reservation;
	}

	public VendeurInterneDTO getVendeur() {
		return vendeur;
	}

	public void setVendeur(VendeurInterneDTO vendeur) {
		this.vendeur = vendeur;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
