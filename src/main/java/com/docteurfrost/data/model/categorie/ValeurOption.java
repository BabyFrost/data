package com.docteurfrost.data.model.categorie;

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
	@Column(name="ID")
	private String id;
	
	@Column(name="VALEUR")
	private String valeur;

	@ManyToOne
	@JoinColumn(name="OPTION_CATEGORIE")
	@JsonBackReference(value="valeur_option")
	private OptionCategorie option;
	
	public ValeurOption( ) { }
	
	public ValeurOption( String valeur, OptionCategorie option ) {
		this.valeur = valeur.toUpperCase();
		this.id = option.getId().toUpperCase()+"_"+this.valeur;
		this.option = option;
	}

	public String getId() {
		return id;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur.toUpperCase();
	}

	public OptionCategorie getOption() {
		return option;
	}

	public void setOption(OptionCategorie option) {
		this.option = option;
	}
}
