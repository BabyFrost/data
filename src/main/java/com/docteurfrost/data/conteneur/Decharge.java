package com.docteurfrost.data.conteneur;

public class Decharge implements ConteneurState {
	
	@SuppressWarnings("unused")
	private Conteneur conteneur;
	
	public Decharge() { }
	
	public Decharge(Conteneur conteneur) {
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
		
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
