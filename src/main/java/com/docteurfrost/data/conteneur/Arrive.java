package com.docteurfrost.data.conteneur;

public class Arrive implements ConteneurState {
	
	private Conteneur conteneur;
	
	public Arrive() { }
	
	public Arrive(Conteneur conteneur) {
		this.conteneur = conteneur;
	}

	@Override
	public void depart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void arrive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void decharger() {
		conteneur.setState( conteneur.getDecharge() );
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
