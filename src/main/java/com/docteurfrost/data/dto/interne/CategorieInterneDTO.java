package com.docteurfrost.data.dto.interne;

import java.util.List;
import java.util.ArrayList;

import com.docteurfrost.data.dto.MarqueDTO;
import com.docteurfrost.data.model.Marque;
import com.docteurfrost.data.model.categorie.Categorie;
import com.docteurfrost.data.model.categorie.OptionCategorie;

public class CategorieInterneDTO {
	
	private String nom;
	private String libelle;
	private List<OptionCategorieInterneDTO> options;
	private List<MarqueDTO> marques;
	
	public CategorieInterneDTO() { }

	public CategorieInterneDTO( Categorie categorie ) {
		this.nom = categorie.getNom();
		this.libelle = categorie.getLibelle();
		
		/*
		 * options = new ArrayList<>(); List<OptionCategorie> optionsCategorie;
		 * optionsCategorie = new ArrayList<>( categorie.getOptions() ); for ( int i=0;
		 * i < optionsCategorie.size(); i++ ) { //options.add( new OptionCategorieDTO(
		 * optionsCategorie.get(i) ) ); options.add( new OptionCategorieDTO(
		 * optionsCategorie.get(i) ) ); }
		 */
		
		options = new ArrayList<>();
		List<OptionCategorie> optionsCategorie;
		optionsCategorie =  new ArrayList<>( categorie.getOptions() );
		for ( int i=0; i < optionsCategorie.size(); i++ ) {
			options.add( new OptionCategorieInterneDTO( optionsCategorie.get(i) ) );
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

	public List<OptionCategorieInterneDTO> getOptions() {
		return options;
	}

	public void setOptions(List<OptionCategorieInterneDTO> options) {
		this.options = options;
	}

	public List<MarqueDTO> getMarques() {
		return marques;
	}

	public void setMarques(List<MarqueDTO> marques) {
		this.marques = marques;
	}
	
}
