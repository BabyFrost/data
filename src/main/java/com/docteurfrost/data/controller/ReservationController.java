package com.docteurfrost.data.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.dto.ReservationDTO;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Avance;
import com.docteurfrost.data.model.Client;
import com.docteurfrost.data.model.Utilisateur;
import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.model.reservation.Reservation;
import com.docteurfrost.data.service.ArticleService;
import com.docteurfrost.data.service.AvanceService;
import com.docteurfrost.data.service.ClientService;
import com.docteurfrost.data.service.ReservationService;
import com.docteurfrost.data.service.UtilisateurService;

@RequestMapping("/reservations")
@RestController
public class ReservationController {
	
	@Autowired
	ReservationService reservationService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private AvanceService avanceService;
	
	@GetMapping("/{idReservation}")
	@ResponseBody
	public ReservationDTO getReservationById( @PathVariable("idReservation") int idReservation ) throws ResourceNotFoundException {
		return new ReservationDTO( reservationService.getReservationById( idReservation ) );
	}
	
	@GetMapping()
	@ResponseBody
	public List<ReservationDTO> getAllReservations(  ) throws ResourceNotFoundException, ParseException {
		
		List<Reservation> reservations = new ArrayList<>();	
		reservations = reservationService.getAllReservation();
		
		List<ReservationDTO> reservationsDTO = new ArrayList<>();
		for (int i=0; i<reservations.size(); i++) {
			reservationsDTO.add( new ReservationDTO( reservations.get(i) ) );
		}
		
		System.out.println( "Date Out : " + reservationsDTO.get(0).getDate() );
		return reservationsDTO;
	}
	
	@PostMapping()
	@ResponseBody
	@Transactional
	public ResponseEntity<ReservationDTO> createReservation( @Valid @RequestBody ReservationDTO reservationDTO ) throws ResourceNotFoundException {
		
		Article article = articleService.getArticleById( reservationDTO.getArticle().getId() );
		Client client = clientService.getClientById( reservationDTO.getClient().getTelephone() );
		Utilisateur vendeur = utilisateurService.getUtilisateurById( reservationDTO.getVendeur().getId() );
		Reservation reservation = new Reservation( "Reservation "+article.getId(), client, article, vendeur, reservationDTO.getTotalAvances() );
		Avance avance = new Avance( reservation, vendeur, reservationDTO.getTotalAvances() );
		
		article.reserver();
		avanceService.createAvance(avance);
		articleService.updateArticle(article);
		ReservationDTO responseReservationDTO = new ReservationDTO( reservationService.createReservation(reservation) );
		
		return new ResponseEntity<>( responseReservationDTO, HttpStatus.OK );
	}
	
	@PatchMapping("/{idReservation}/rembourser")
	@ResponseBody
	@Transactional
	public ResponseEntity<ReservationDTO> rembourserReservation( @PathVariable("idReservation") int idReservation ) throws ResourceNotFoundException {
		
		Reservation reservation = reservationService.getReservationById(idReservation);
		System.out.println("Reservation Controller : Reservation state = "+reservation.getState().toString() );
		reservation.rembourser();
		System.out.println("Reservation Controller : Reservation state = "+reservation.getState().toString() );
	
		ReservationDTO responseReservationDTO = new ReservationDTO( reservationService.updateReservation(reservation) );
		return new ResponseEntity<>( responseReservationDTO, HttpStatus.OK );
	}

}
