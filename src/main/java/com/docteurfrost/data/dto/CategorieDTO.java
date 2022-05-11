package com.docteurfrost.data.dto;

import java.util.List;
import java.util.ArrayList;

import com.docteurfrost.data.categorie.Categorie;
import com.docteurfrost.data.categorie.OptionCategorie;
import com.docteurfrost.data.model.Marque;

public class CategorieDTO {
	
	private String nom;
	private String libelle;
	private List<OptionCategorieDTO> options;
	private List<MarqueDTO> marques;
	
	public CategorieDTO() { }

	public CategorieDTO( Categorie categorie ) {
		this.nom = categorie.getNom();
		this.libelle = categorie.getLibelle();
		
		options = new ArrayList<>();
		List<OptionCategorie> optionsCategorie;
		optionsCategorie =  new ArrayList<>( categorie.getOptions() );
		for ( int i=0; i < optionsCategorie.size(); i++ ) {
			options.add( new OptionCategorieDTO( optionsCategorie.get(i) ) );
		}
		
		marques = new ArrayList<>();
		List<Marque> marquesCategorie;
		marquesCategorie =  new ArrayList<>( categorie.getMarques() );
		for ( int i=0; i < marquesCategorie.size(); i++ ) {
			marques.add( new MarqueDTO( marquesCategorie.get(i) ) );
		}
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<OptionCategorieDTO> getOptions() {
		return options;
	}

	public void setOptionsCategorieDTO(List<OptionCategorieDTO> options) {
		this.options = options;
	}

	public void setOptions(List<OptionCategorieDTO> options) {
		this.options = options;
	}

	public List<MarqueDTO> getMarques() {
		return marques;
	}

	public void setMarques(List<MarqueDTO> marques) {
		this.marques = marques;
	}
	
}
