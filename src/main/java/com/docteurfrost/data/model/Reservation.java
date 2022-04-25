package com.docteurfrost.data.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_RESERVATIONS")
public class Reservation extends Operation {
	
	@OneToMany(mappedBy = "reservation", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="avances_reservation")
	private Collection<Avance> avances = new ArrayList<>();

	@Column(name="MONTANT_AVANCE")
	private int montantAvance;
	
	@Column(name="STATUS")
	private String status;
}
