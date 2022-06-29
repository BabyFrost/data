package com.docteurfrost.data.model.reservation;

public interface ReservationState {
	
	public abstract void abandonner();
	public abstract void avancer( int montant);
	public abstract void completer();
	public abstract void rembourser();

}
