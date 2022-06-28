package com.docteurfrost.data.dto;

import java.util.ArrayList;
import java.util.List;

import com.docteurfrost.data.dto.interne.ValeurOptionInterneDTO;
import com.docteurfrost.data.model.categorie.OptionCategorie;
import com.docteurfrost.data.model.categorie.ValeurOption;

public class OptionCategorieDTO {

	private String id;
	private String nom;
	private String categorie;
	private String libelle;
	private Boolean isNumerique;
	private Boolean isFree;
	private Boolean isBoolean;
	private List<ValeurOptionInterneDTO> valeurs;
	
	public OptionCategorieDTO() { 
		
		this.id = this.nom+"_"+this.categorie;
		
	}

	public OptionCategorieDTO( OptionCategorie optionCategorie ) {
		this.nom = optionCategorie.getNom();
		this.categorie = optionCategorie.getCategorie().getNom();
		this.id = this.nom+"_"+this.categorie;
		this.libelle = optionCategorie.getLibelle();
		this.isNumerique = optionCategorie.getIsNumerique();
		this.isFree = optionCategorie.getIsFree();
		this.isBoolean = optionCategorie.getIsBoolean();
		
		/*
		 * valeurs = new ArrayList<>(); ValeurOptionDTO valeurOptionDTO;
		 * List<ValeurOption> valeurOption; valeurOption = new ArrayList<>(
		 * optionCategorie.getValeurs() ); for ( int i=0; i < valeurOption.size(); i++ )
		 * { valeurOptionDTO = new ValeurOptionDTO( valeurOption.get(i) );
		 * valeurs.add(valeurOptionDTO); }
		 */
		
		valeurs = new ArrayList<>();
		ValeurOptionInterneDTO valeurOptionDTO;
		List<ValeurOption> valeurOption;
		valeurOption =  new ArrayList<>( optionCategorie.getValeurs() );
		for ( int i=0; i < valeurOption.size(); i++ ) {
			valeurOptionDTO = new ValeurOptionInterneDTO( valeurOption.get(i) );
			valeurs.add(valeurOptionDTO);
		}
		
	}
	
	
	public String getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
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

	public List<ValeurOptionInterneDTO> getValeurs() {
		return valeurs;
	}

	public void setValeurs(List<ValeurOptionInterneDTO> valeurs) {
		this.valeurs = valeurs;
	}
	
}
