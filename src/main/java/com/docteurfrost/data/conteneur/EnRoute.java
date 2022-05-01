package com.docteurfrost.data.conteneur;

public class EnRoute implements ConteneurState {
	
	private Conteneur conteneur;
	
	public EnRoute() { }
	
	public EnRoute( Conteneur conteneur ) {
		this.conteneur = conteneur;
	}

	@Override
	public void depart() {
		
	}

	@Override
	public void arrive() {
		conteneur.setState( conteneur.getArrive() );
	}

	@Override
	public void decharger() {
		
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
