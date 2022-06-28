package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.dto.ReservationDTO;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.reservation.Reservation;
import com.docteurfrost.data.service.ReservationService;

@RequestMapping("/reservations")
@RestController
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@GetMapping("/{idReservation}")
	@ResponseBody
	public ReservationDTO getReservationById( @PathVariable("idReservation") int idReservation ) throws ResourceNotFoundException {
		return new ReservationDTO( reservationService.getReservationById( idReservation ) );
	}
	
	@GetMapping()
	@ResponseBody
	public List<ReservationDTO> getAllReservations(  ) throws ResourceNotFoundException {
		
		List<Reservation> reservations = new ArrayList<>();	
		reservations = reservationService.getAllReservation();
		
		List<ReservationDTO> reservationDTO = new ArrayList<>();
		for (int i=0; i<reservations.size(); i++) {
			reservationDTO.add( new ReservationDTO( reservations.get(i) ) );
		}
		
		return reservationDTO;
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<ReservationDTO> getReservationById( @Valid @RequestBody ReservationDTO reservationDTO ) throws ResourceNotFoundException {
		ReservationDTO responseReservationDTO = new ReservationDTO( reservationService.getReservationById( 1 ) );
		return new ResponseEntity<>( responseReservationDTO, HttpStatus.OK );
	}

}
