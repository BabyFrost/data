package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
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

import com.docteurfrost.data.dto.AvanceDTO;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Avance;
import com.docteurfrost.data.model.Utilisateur;
import com.docteurfrost.data.model.reservation.Complet;
import com.docteurfrost.data.model.reservation.Reservation;
import com.docteurfrost.data.service.AvanceService;
import com.docteurfrost.data.service.ReservationService;
import com.docteurfrost.data.service.UtilisateurService;

@RequestMapping("/avances")
@RestController
public class AvanceController {
	
	@Autowired
	AvanceService avanceService;
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	UtilisateurService utilisateurService;
	
	@Autowired
	VenteController venteController;
	
	@GetMapping("/{idAvance}")
	@ResponseBody
	public AvanceDTO getAvanceById( @PathVariable("idAvance") int idAvance ) throws ResourceNotFoundException {
		return new AvanceDTO( avanceService.getAvanceById( idAvance ) );
	}
	
	@GetMapping()
	@ResponseBody
	public List<AvanceDTO> getAllAvances(  ) throws ResourceNotFoundException {
		
		List<Avance> avances = new ArrayList<>();	
		avances = avanceService.getAllAvance();
		
		List<AvanceDTO> avancesDTO = new ArrayList<>();
		for (int i=0; i<avances.size(); i++) {
			avancesDTO.add( new AvanceDTO( avances.get(i) ) );
		}
		
		return avancesDTO;
	}
	
	@PostMapping()
	@ResponseBody
	@Transactional
	public ResponseEntity<AvanceDTO> getAvanceById( @Valid @RequestBody AvanceDTO avanceDTO ) throws ResourceNotFoundException {

		Reservation reservation = reservationService.getReservationById( avanceDTO.getReservation() );
		Utilisateur vendeur = utilisateurService.getUtilisateurById( avanceDTO.getVendeur().getId() );
		
		int montantAvance = reservation.avancer( avanceDTO.getMontant() );
		Avance avance = new Avance( reservation, vendeur, montantAvance );
		
		if ( reservation.getState() instanceof Complet ) {
			venteController.completerReservation( reservation, vendeur );
		}
		
		AvanceDTO responseAvanceDTO = new AvanceDTO( avanceService.createAvance( avance ) );
		reservation = reservationService.updateReservation(reservation);
		
		return new ResponseEntity<>( responseAvanceDTO, HttpStatus.OK );
	}

}
