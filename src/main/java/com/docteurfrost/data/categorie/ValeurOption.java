package com.docteurfrost.data.categorie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="T_VALEURS_OPTION")
public class ValeurOption {
	
	@Id 
	@Column(name="NOM")
	private String valeur;

	@ManyToOne
//	@MapsId("idOption")
	@JoinColumn(name="OPTION_CATEGORIE")
	@JsonBackReference(value="valeur_option")
	private OptionCategorie option;
	
	public ValeurOption( ) { }
	
	public ValeurOption( String nom ) {
//		this.nom = nom;
	}
}
