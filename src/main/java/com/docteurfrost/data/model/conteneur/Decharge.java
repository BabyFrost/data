package com.docteurfrost.data.model.conteneur;

import com.docteurfrost.data.exception.BadRequestException;

public class Decharge implements ConteneurState {
	
	@SuppressWarnings("unused")
	private Conteneur conteneur;
	
	public Decharge() { }
	
	public Decharge(Conteneur conteneur) {
		this.conteneur = conteneur;
	}

	@Override
	public void depart() {
		throw new BadRequestException("Depart Impossible");
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
