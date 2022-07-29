package com.docteurfrost.data.model.reservation;

public class Partiel implements ReservationState {
	
	private Reservation reservation;
	
	public Partiel() { }
	
	public Partiel(Reservation reservation) {
		this.reservation = reservation;
	}
	
	@Override
	public void abandonner() {
		reservation.setState( reservation.getAbandonne() );
	}
	
	@Override
	public void avancer( int montant ) {
		reservation.setTotalAvances( reservation.getTotalAvances()+montant );
	}
	
	@Override
	public void completer() {
		reservation.setState( reservation.getComplet() );
		
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

	@Override
	public void rembourser() {
		reservation.getArticle().rembourserReservation();
		System.out.println("Partiel : Reservation state = "+reservation.getState().toString() );
		reservation.setState( reservation.getRembourse() );
	}

}
