package com.docteurfrost.data.model.reservation;

public class Complet implements ReservationState {
	
	private Reservation reservation;
	
	public Complet() { }
	
	public Complet(Reservation reservation) {
		this.reservation = reservation;
	}
	
	@Override
	public void avancer() {
		
	}

	@Override
	public void abandonner() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void completer() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}