package com.docteurfrost.data.dto;

import com.docteurfrost.data.categorie.ValeurOption;

public class ValeurOptionShortDTO {
	private String valeur;
	
	
	public ValeurOptionShortDTO( ) { }
	
	public ValeurOptionShortDTO( ValeurOption valeurOption ) {
		this.valeur = valeurOption.getValeur();
		
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
}
