package com.docteurfrost.data.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.docteurfrost.data.conteneur.Arrive;
import com.docteurfrost.data.conteneur.Conteneur;
import com.docteurfrost.data.conteneur.EnRoute;
import com.docteurfrost.data.dto.ConteneurDTO;
import com.docteurfrost.data.repository.ConteneurRepository;
import com.docteurfrost.data.tools.DateStringConverter;

@RequestMapping("/conteneurs")
@RestController
public class ConteneurController {

	@Autowired
	private ConteneurRepository conteneurRepository;
	
	@GetMapping()
	@ResponseBody
	public Iterable<ConteneurDTO> getAllContainers( ) {
		List<Conteneur> conteneurs = new ArrayList<>();
		conteneurRepository.findAll().forEach(conteneurs::add);
		
		List<ConteneurDTO> conteneursDTO = new ArrayList<>();
		for (int i=0; i<conteneurs.size(); i++) {
			conteneursDTO.add( new ConteneurDTO( conteneurs.get(i) ) );
		}
		
		return conteneursDTO;
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<String> saveConteneury(@RequestBody ConteneurDTO conteneurDTO) throws ParseException {
		
		System.out.println("Inside the post");
		
		if ( conteneurRepository.findById(conteneurDTO.getId()).isPresent() ) {
			return new ResponseEntity<>( "Ce Conteneur existe deja", HttpStatus.CONFLICT );
		}
		
		Conteneur conteneur = new Conteneur( conteneurDTO.getId(), conteneurDTO.getNom(), conteneurDTO.getPays(), DateStringConverter.stringToDate( conteneurDTO.getDateDepart() ), DateStringConverter.stringToDate( conteneurDTO.getDateArrivee() ), DateStringConverter.stringToDate( conteneurDTO.getDateDechargement() ) );
		conteneurRepository.save(conteneur);
		
		return new ResponseEntity<>( "Conteneur cree", HttpStatus.CREATED );
	}
	
	@PutMapping()
	@ResponseBody
	public ResponseEntity<String> modifyConteneur(@RequestBody ConteneurDTO conteneurDTO) throws ParseException {
		
		Optional<Conteneur> conteneurTmp = conteneurRepository.findByNom( conteneurDTO.getNom() );
		if ( conteneurTmp.isPresent() ) {
			Conteneur conteneur = conteneurTmp.get();
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
			conteneurRepository.save(conteneur);
			
			return new ResponseEntity<>( "Conteneur Modifie", HttpStatus.OK );
		} else {
			return new ResponseEntity<>( "Ce Conteneur n'existe pas", HttpStatus.CONFLICT );
		}
		
	}
	
	@PatchMapping("/depart/{idConteneur}")
	@ResponseBody
	public ResponseEntity<String> miseEnRouteConteneur( @PathVariable("idConteneur") int idConteneur, @RequestParam String date ) throws ParseException {
		
		Conteneur conteneur;
		Optional<Conteneur> conteneurTmp = conteneurRepository.findById(idConteneur);  
		if ( conteneurTmp.isPresent() ) {
			
			conteneur = conteneurTmp.get();
			if ( conteneur.getDateDepart() == null ) {
				conteneur.setDateDepart( DateStringConverter.stringToDate( date ) );
				conteneur.getState().depart();
				conteneurRepository.save( conteneur );
				return new ResponseEntity<>( "Status Modifie", HttpStatus.OK );
			} else {
				return new ResponseEntity<>( "Conteneur deja Parti", HttpStatus.ALREADY_REPORTED );
			}
			
		} else {
			return new ResponseEntity<>( "Conteneur inexistant", HttpStatus.CONFLICT );
		}
	}
	
	@PatchMapping("/arrive/{idConteneur}")
	@ResponseBody
	public ResponseEntity<String> arriveConteneur( @PathVariable("idConteneur") int idConteneur, @RequestParam String date ) throws ParseException {
		
		Conteneur conteneur;
		Optional<Conteneur> conteneurTmp = conteneurRepository.findById(idConteneur);  
		if ( conteneurTmp.isPresent() ) {
			
			conteneur = conteneurTmp.get();
			if ( conteneur.getDateArrivee() == null ) {
				conteneur.setDateArrivee( DateStringConverter.stringToDate( date ) );
				
				if ( !( conteneur.getState() instanceof EnRoute ) ) {
					return new ResponseEntity<>( "Conteneur toujours en chargement", HttpStatus.BAD_REQUEST );
				}
				
				if ( conteneur.getDateArrivee().before( conteneur.getDateDepart() ) ) {
					return new ResponseEntity<>( "Date < Date Depart", HttpStatus.BAD_REQUEST );
				}
				
				conteneur.getState().arrive();
				conteneurRepository.save( conteneur );
				return new ResponseEntity<>( "Status Modifie", HttpStatus.OK );
			} else {
				return new ResponseEntity<>( "Conteneur deja Arrivé", HttpStatus.ALREADY_REPORTED );
			}
			
		} else {
			return new ResponseEntity<>( "Conteneur inexistant", HttpStatus.CONFLICT );
		}
	}
	
	@PatchMapping("/dechargement/{idConteneur}")
	@ResponseBody
	public ResponseEntity<String> dechargementConteneur( @PathVariable("idConteneur") int idConteneur, @RequestParam String date ) throws ParseException {
		
		Conteneur conteneur;
		Optional<Conteneur> conteneurTmp = conteneurRepository.findById(idConteneur);  
		if ( conteneurTmp.isPresent() ) {
			
			conteneur = conteneurTmp.get();
			if ( conteneur.getDateDechargement() == null ) {
				conteneur.setDateDechargement( DateStringConverter.stringToDate( date ) );
				
				if ( !( conteneur.getState() instanceof Arrive ) ) {
					return new ResponseEntity<>( "Recevoir d'abord le conteneur", HttpStatus.BAD_REQUEST );
				}
				
				if ( conteneur.getDateDechargement().before( conteneur.getDateArrivee() ) ) {
					return new ResponseEntity<>( "Date < Date Arrivee", HttpStatus.BAD_REQUEST );
				}
				
				conteneur.getState().decharger();
				conteneurRepository.save( conteneur );
				return new ResponseEntity<>( "Status Modifie", HttpStatus.OK );
			} else {
				return new ResponseEntity<>( "Conteneur deja Dechargé", HttpStatus.ALREADY_REPORTED );
			}
			
		} else {
			return new ResponseEntity<>( "Conteneur inexistant", HttpStatus.CONFLICT );
		}
	}
	
}
