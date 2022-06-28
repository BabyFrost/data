package com.docteurfrost.data.model.reservation;

public interface ReservationState {
	
	public abstract void abandonner();
	public abstract void avancer();
	public abstract void completer();

}
