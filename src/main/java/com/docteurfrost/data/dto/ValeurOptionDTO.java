package com.docteurfrost.data.dto;

import com.docteurfrost.data.categorie.ValeurOption;

public class ValeurOptionDTO {
	
//	private int id;
	private String option;
	private String valeur;
	
	
	public ValeurOptionDTO( ) { }
	
	public ValeurOptionDTO( ValeurOption valeurOption ) {
		this.option = valeurOption.getOption().getId().toUpperCase();
		this.valeur = valeurOption.getValeur();
		
	}

	public String getValeur() {
		return valeur;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option.toUpperCase();
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
}
