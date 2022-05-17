package com.docteurfrost.data.dto;

import java.util.ArrayList;
import java.util.List;

import com.docteurfrost.data.categorie.OptionCategorie;
import com.docteurfrost.data.categorie.ValeurOption;

public class OptionCategorieDTO {

	private String id;
	private String nom;
	private String categorie;
	private String libelle;
	private Boolean isNumerique;
	private Boolean isFree;
	private Boolean isBoolean;
	private List<ValeurOptionShortDTO> valeurs;
	
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
		ValeurOptionShortDTO valeurOptionDTO;
		List<ValeurOption> valeurOption;
		valeurOption =  new ArrayList<>( optionCategorie.getValeurs() );
		for ( int i=0; i < valeurOption.size(); i++ ) {
			valeurOptionDTO = new ValeurOptionShortDTO( valeurOption.get(i) );
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

	public List<ValeurOptionShortDTO> getValeurs() {
		return valeurs;
	}

	public void setValeurs(List<ValeurOptionShortDTO> valeurs) {
		this.valeurs = valeurs;
	}
	
}
