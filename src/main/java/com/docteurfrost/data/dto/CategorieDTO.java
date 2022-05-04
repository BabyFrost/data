package com.docteurfrost.data.dto;

import java.util.List;
import java.util.ArrayList;

import com.docteurfrost.data.categorie.Categorie;
import com.docteurfrost.data.categorie.OptionCategorie;

public class CategorieDTO {
	
	private String nom;
	private String libelle;
	private List<OptionCategorieDTO> options;
	
	public CategorieDTO() { }

	public CategorieDTO( Categorie categorie ) {
		this.nom = categorie.getNom();
		this.libelle = categorie.getLibelle();
		
		options = new ArrayList<>();
		OptionCategorieDTO optionCategorieDTO;
		List<OptionCategorie> optionsCategorie;
		optionsCategorie =  new ArrayList<>( categorie.getOptions() );
		for ( int i=0; i < optionsCategorie.size(); i++ ) {
			optionCategorieDTO = new OptionCategorieDTO( optionsCategorie.get(i) );
			options.add(optionCategorieDTO);
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
	
}
