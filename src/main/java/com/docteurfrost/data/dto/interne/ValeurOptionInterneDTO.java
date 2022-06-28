package com.docteurfrost.data.dto.interne;

import com.docteurfrost.data.model.categorie.ValeurOption;

public class ValeurOptionInterneDTO {
	private String valeur;
	
	
	public ValeurOptionInterneDTO( ) { }
	
	public ValeurOptionInterneDTO( ValeurOption valeurOption ) {
		this.valeur = valeurOption.getValeur();
		
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
	
}
