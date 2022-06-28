package com.docteurfrost.data.model.reservation;

public class Abandonne implements ReservationState {
	
	private Reservation reservation;
	
	public Abandonne() { }
	
	public Abandonne(Reservation reservation) {
		this.reservation = reservation;
	}
	
	@Override
	public void abandonner() {
		reservation.setLibelle("");	
	}
	
	@Override
	public void avancer() {
		reservation.setLibelle("");
	}
	
	@Override
	public void completer() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}