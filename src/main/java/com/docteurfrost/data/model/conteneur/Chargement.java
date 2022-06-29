package com.docteurfrost.data.model.conteneur;

import com.docteurfrost.data.exception.BadRequestException;

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
		throw new BadRequestException("Arrive Impossible");
	}

	@Override
	public void decharger() {
		throw new BadRequestException("Dechargement Impossible");
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}