package com.docteurfrost.data.model.conteneur;

public class Chargement implements ConteneurState {
	
	private Conteneur conteneur;
	
	public Chargement() { }
	
	public Chargement(Conteneur conteneur) {
		this.conteneur = conteneur;
	}

	@Override
	public void depart() {
		conteneur.setState( conteneur.getEnRoute() );
	}

	@Override
	public void arrive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decharger() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}