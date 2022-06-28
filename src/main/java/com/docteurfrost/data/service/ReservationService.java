package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.reservation.Reservation;
import com.docteurfrost.data.repository.ReservationRepository;

@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	public Reservation getReservationById( int id ) throws ResourceNotFoundException {	
		return reservationRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No such Reservation !") );
	}
	
	public List<Reservation> getAllReservation() throws ResourceNotFoundException {	
		List<Reservation> reservations = new ArrayList<>();	
		reservationRepository.findAll().forEach(reservations::add);	
		return reservations;
	}
	
	public Reservation saveReservation( Reservation reservation ) {
		return reservationRepository.save(reservation);
	}
	
	public Reservation createReservation( Reservation reservation ) throws ResourceConflictException {	
		Optional<Reservation> reservationTmp = reservationRepository.findById( reservation.getId() );
		if ( reservationTmp.isPresent() ) { throw new ResourceConflictException("Reservation existe deja"); }
		return saveReservation( reservation );
	}
	
}
