package com.docteurfrost.data.model.reservation;

import com.docteurfrost.data.exception.BadRequestException;

public class Complet implements ReservationState {
	
	private Reservation reservation;
	
	public Complet() { }
	
	public Complet(Reservation reservation) {
		this.reservation = reservation;
	}
	
	@Override
	public void avancer( int montant ) {
		throw new BadRequestException("Avance Impossible");
	}

	@Override
	public void abandonner() {
		throw new BadRequestException("Abandon Impossible");
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