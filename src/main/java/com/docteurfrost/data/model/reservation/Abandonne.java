package com.docteurfrost.data.model.reservation;

import com.docteurfrost.data.exception.BadRequestException;

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
	public void avancer( int montant ) {
		reservation.setLibelle("");
	}
	
	@Override
	public void completer() {
		throw new BadRequestException("Completion Impossible");
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

	@Override
	public void rembourser() {
		// TODO Auto-generated method stub
		
	}

}