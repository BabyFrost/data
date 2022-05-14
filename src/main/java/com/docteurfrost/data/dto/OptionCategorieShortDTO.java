package com.docteurfrost.data.dto;

import java.util.ArrayList;
import java.util.List;

import com.docteurfrost.data.categorie.OptionCategorie;
import com.docteurfrost.data.categorie.ValeurOption;

public class OptionCategorieShortDTO {
	private String nom;
	private Boolean isNumerique;
	private Boolean isFree;
	private Boolean isBoolean;
	private List<ValeurOptionDTO> valeurs;
	
	public OptionCategorieShortDTO() { }

	public OptionCategorieShortDTO( OptionCategorie optionCategorie ) {
		this.nom = optionCategorie.getNom();
		this.isNumerique = optionCategorie.getIsNumerique();
		this.isFree = optionCategorie.getIsFree();
		this.isBoolean = optionCategorie.getIsBoolean();
		
		valeurs = new ArrayList<>();
		ValeurOptionDTO valeurOptionDTO;
		List<ValeurOption> valeurOption;
		valeurOption =  new ArrayList<>( optionCategorie.getValeurs() );
		for ( int i=0; i < valeurOption.size(); i++ ) {
			valeurOptionDTO = new ValeurOptionDTO( valeurOption.get(i) );
			valeurs.add(valeurOptionDTO);
		}
		
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Boolean getIsNumerique() {
		return isNumerique;
	}

	public void setIsNumerique(Boolean isNumerique) {
		this.isNumerique = isNumerique;
	}

	public Boolean getIsFree() {
		return isFree;
	}

	public void setIsFree(Boolean isFree) {
		this.isFree = isFree;
	}

	public Boolean getIsBoolean() {
		return isBoolean;
	}

	public void setIsBoolean(Boolean isBoolean) {
		this.isBoolean = isBoolean;
	}

	public List<ValeurOptionDTO> getValeurs() {
		return valeurs;
	}

	public void setValeurs(List<ValeurOptionDTO> valeurs) {
		this.valeurs = valeurs;
	}
	
}
