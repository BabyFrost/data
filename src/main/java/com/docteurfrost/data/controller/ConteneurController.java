package com.docteurfrost.data.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.conteneur.Conteneur;
import com.docteurfrost.data.dto.ConteneurDTO;
import com.docteurfrost.data.exception.BadRequestException;
import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.service.ConteneurService;
import com.docteurfrost.data.tools.DateStringConverter;

@RequestMapping("/conteneurs")
@RestController
public class ConteneurController {

	@Autowired
	private ConteneurService conteneurService;
	
	@GetMapping()
	@ResponseBody
	public List<ConteneurDTO> getAllConteneursDTO( ) {
		List<Conteneur> conteneurs = conteneurService.getAllConteneurs();
		List<ConteneurDTO> conteneursDTO = new ArrayList<>();
		for (int i=0; i<conteneurs.size(); i++) {
			conteneursDTO.add( new ConteneurDTO( conteneurs.get(i) ) );
		}
		return conteneursDTO;
	}
	
	@GetMapping("/{idConteneur}")
	@ResponseBody
	public ConteneurDTO getConteneurById( @PathVariable("idConteneur") int idConteneur ) throws ResourceNotFoundException {
		Conteneur conteneur = conteneurService.getConteneurById(idConteneur);
		return new ConteneurDTO( conteneur );
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<ConteneurDTO> saveConteneurDTO(@RequestBody ConteneurDTO conteneurDTO) throws ParseException, ResourceConflictException {	
		Conteneur conteneur = new Conteneur( conteneurDTO.getId(), conteneurDTO.getNom(), conteneurDTO.getPays(), DateStringConverter.stringToDate( conteneurDTO.getDateDepart() ), DateStringConverter.stringToDate( conteneurDTO.getDateArrivee() ), DateStringConverter.stringToDate( conteneurDTO.getDateDechargement() ) );
		ConteneurDTO responseConteneurDTO = new ConteneurDTO( conteneurService.createConteneur(conteneur) );
		return new ResponseEntity<>( responseConteneurDTO, HttpStatus.CREATED );
	}
	
	@PutMapping()
	@ResponseBody
	public ResponseEntity<ConteneurDTO> modifyConteneurDTO(@RequestBody ConteneurDTO conteneurDTO) throws ParseException, ResourceNotFoundException {	
		Conteneur conteneur = conteneurService.getConteneurById( conteneurDTO.getId() );
		conteneur.setNom( conteneurDTO.getNom() );
		conteneur.setPays( conteneurDTO.getPays() );
		if ( conteneur.getDateDepart() != null ) {
			conteneur.setDateDepart( DateStringConverter.stringToDate( conteneurDTO.getDateDepart() ) );
		}
		if ( conteneur.getDateArrivee() != null ) {
			conteneur.setDateArrivee( DateStringConverter.stringToDate( conteneurDTO.getDateArrivee() ) );
		}
		if ( conteneur.getDateDechargement() != null ) {
			conteneur.setDateDechargement( DateStringConverter.stringToDate( conteneurDTO.getDateDechargement() ) );
		}
		ConteneurDTO responseConteneurDTO = new ConteneurDTO( conteneurService.saveConteneur(conteneur) );
		return new ResponseEntity<>( responseConteneurDTO, HttpStatus.OK );
	}
	
	@PatchMapping("/{idConteneur}/depart")
	@ResponseBody
	public ResponseEntity<ConteneurDTO> miseEnRouteConteneur( @PathVariable("idConteneur") int idConteneur, @RequestParam String date ) throws ParseException, ResourceNotFoundException, BadRequestException {	
		Conteneur conteneur = conteneurService.getConteneurById(idConteneur);
		ConteneurDTO responseConteneurDTO = new ConteneurDTO( conteneurService.miseEnRouteConteneur(conteneur, date) );
		return new ResponseEntity<>( responseConteneurDTO , HttpStatus.OK );
	}
	
	@PatchMapping("/{idConteneur}/arrive")
	@ResponseBody
	public ResponseEntity<ConteneurDTO> arriveConteneur( @PathVariable("idConteneur") int idConteneur, @RequestParam String date ) throws ParseException, ResourceNotFoundException, BadRequestException {
		Conteneur conteneur = conteneurService.getConteneurById(idConteneur);
		ConteneurDTO responseConteneurDTO = new ConteneurDTO( conteneurService.arriveConteneur(conteneur, date) );
		return new ResponseEntity<>( responseConteneurDTO, HttpStatus.OK );
	}
	
	@PatchMapping("/{idConteneur}/dechargement")
	@ResponseBody
	public ResponseEntity<ConteneurDTO> dechargementConteneur( @PathVariable("idConteneur") int idConteneur, @RequestParam String date ) throws ParseException, ResourceNotFoundException, BadRequestException {
		Conteneur conteneur = conteneurService.getConteneurById(idConteneur);
		ConteneurDTO responseConteneurDTO = new ConteneurDTO( conteneurService.dechargementConteneur(conteneur, date) );
		return new ResponseEntity<>( responseConteneurDTO, HttpStatus.OK );
	}
	
}
