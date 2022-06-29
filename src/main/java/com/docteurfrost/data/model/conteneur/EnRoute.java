package com.docteurfrost.data.model.conteneur;

import com.docteurfrost.data.exception.BadRequestException;

public class EnRoute implements ConteneurState {
	
	private Conteneur conteneur;
	
	public EnRoute() { }
	
	public EnRoute( Conteneur conteneur ) {
		this.conteneur = conteneur;
	}

	@Override
	public void depart() {
		throw new BadRequestException("Depart Impossible");
	}

	@Override
	public void arrive() {
		conteneur.setState( conteneur.getArrive() );
	}

	@Override
	public void decharger() {
		throw new BadRequestException("Dechargement Impossible");
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
