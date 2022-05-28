package com.docteurfrost.data.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.conteneur.Arrive;
import com.docteurfrost.data.conteneur.Conteneur;
import com.docteurfrost.data.conteneur.EnRoute;
import com.docteurfrost.data.dto.ConteneurDTO;
import com.docteurfrost.data.repository.ConteneurRepository;
import com.docteurfrost.data.tools.DateStringConverter;

@Service
public class ConteneurService {
	
	@Autowired
	private ConteneurRepository conteneurRepository;
	
	public List<Conteneur> getAllConteneurs( ) {
		List<Conteneur> conteneurs = new ArrayList<>();
		conteneurRepository.findAll().forEach(conteneurs::add);
		
		return conteneurs;
	}
	
	public void saveConteneur( Conteneur conteneur) {
		conteneurRepository.save(conteneur);
	}
	
	public ResponseEntity<String> saveConteneurDTO( ConteneurDTO conteneurDTO) throws ParseException {
		
		if ( conteneurRepository.findById( conteneurDTO.getId() ).isPresent() ) {
			return new ResponseEntity<>( "Ce Conteneur existe deja", HttpStatus.CONFLICT );
		}
		
		Conteneur conteneur = new Conteneur( conteneurDTO.getId(), conteneurDTO.getNom(), conteneurDTO.getPays(), DateStringConverter.stringToDate( conteneurDTO.getDateDepart() ), DateStringConverter.stringToDate( conteneurDTO.getDateArrivee() ), DateStringConverter.stringToDate( conteneurDTO.getDateDechargement() ) );
		saveConteneur(conteneur);
		
		return new ResponseEntity<>( "Conteneur cree", HttpStatus.CREATED );
	}
	
	public ResponseEntity<String> modifyConteneurDTO( ConteneurDTO conteneurDTO) throws ParseException {
		
		Optional<Conteneur> conteneurTmp = conteneurRepository.findById( conteneurDTO.getId() );
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
			saveConteneur(conteneur);
			
			return new ResponseEntity<>( "Conteneur Modifie", HttpStatus.OK );
		} else {
			return new ResponseEntity<>( "Ce Conteneur n'existe pas", HttpStatus.CONFLICT );
		}
		
	}
	
	public ResponseEntity<String> miseEnRouteConteneur( int idConteneur, String date ) throws ParseException {
		
		Conteneur conteneur;
		Optional<Conteneur> conteneurTmp = conteneurRepository.findById(idConteneur);  
		if ( conteneurTmp.isPresent() ) {
			
			conteneur = conteneurTmp.get();
			if ( conteneur.getDateDepart() == null ) {
				conteneur.setDateDepart( DateStringConverter.stringToDate( date ) );
				conteneur.getState().depart();
				saveConteneur(conteneur);
				return new ResponseEntity<>( "Status Modifie", HttpStatus.OK );
			} else {
				return new ResponseEntity<>( "Conteneur deja Parti", HttpStatus.ALREADY_REPORTED );
			}
			
		} else {
			return new ResponseEntity<>( "Conteneur inexistant", HttpStatus.CONFLICT );
		}
	}
	
	public ResponseEntity<String> arriveConteneur( int idConteneur, String date ) throws ParseException {
		
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
				saveConteneur(conteneur);
				return new ResponseEntity<>( "Status Modifie", HttpStatus.OK );
			} else {
				return new ResponseEntity<>( "Conteneur deja Arrivé", HttpStatus.ALREADY_REPORTED );
			}
			
		} else {
			return new ResponseEntity<>( "Conteneur inexistant", HttpStatus.CONFLICT );
		}
	}
	
	public ResponseEntity<String> dechargementConteneur( int idConteneur, String date ) throws ParseException {
		
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
				saveConteneur(conteneur);
				return new ResponseEntity<>( "Status Modifie", HttpStatus.OK );
			} else {
				return new ResponseEntity<>( "Conteneur deja Dechargé", HttpStatus.ALREADY_REPORTED );
			}
			
		} else {
			return new ResponseEntity<>( "Conteneur inexistant", HttpStatus.CONFLICT );
		}
	}

}
