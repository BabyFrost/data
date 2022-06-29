package com.docteurfrost.data.model.reservation;

public class Rembourse implements ReservationState {
	
	private Reservation reservation;
	
	public Rembourse() { }
	
	public Rembourse(Reservation reservation) {
		this.reservation = reservation;
	}

	@Override
	public void abandonner() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void avancer( int montant ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void completer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rembourser() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
