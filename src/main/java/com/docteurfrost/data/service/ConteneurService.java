package com.docteurfrost.data.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.conteneur.Arrive;
import com.docteurfrost.data.conteneur.Conteneur;
import com.docteurfrost.data.conteneur.EnRoute;
import com.docteurfrost.data.exception.BadRequestException;
import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.repository.ConteneurRepository;
import com.docteurfrost.data.tools.DateStringConverter;

@Service
public class ConteneurService {
	
	@Autowired
	private ConteneurRepository conteneurRepository;
	
	public Conteneur getConteneurById( int id ) throws ResourceNotFoundException {	
		return conteneurRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No such Panier !") );
	}
	
	public List<Conteneur> getAllConteneurs( ) {
		List<Conteneur> conteneurs = new ArrayList<>();
		conteneurRepository.findAll().forEach(conteneurs::add);
		return conteneurs;
	}
	
	public Conteneur saveConteneur( Conteneur conteneur) {
		return conteneurRepository.save(conteneur);
	}
	
	public Conteneur createConteneur( Conteneur conteneur ) throws ResourceConflictException {	
		Optional<Conteneur> conteneurTmp = conteneurRepository.findById( conteneur.getId() );
		if ( conteneurTmp.isPresent() ) { throw new ResourceConflictException("Conteneur existe deja"); }
		return saveConteneur( conteneur );
	}
	
	public Conteneur miseEnRouteConteneur( Conteneur conteneur, String date ) throws ParseException, ResourceNotFoundException, BadRequestException {
		
		if ( conteneur.getDateDepart() == null ) {
			conteneur.setDateDepart( DateStringConverter.stringToDate( date ) );
			conteneur.getState().depart();
			conteneur = saveConteneur(conteneur);
			return conteneur;
		} else {
			throw new BadRequestException("Conteneur deja mis en route");
		}
	}
	
	public Conteneur arriveConteneur( Conteneur conteneur, String date ) throws ParseException, ResourceNotFoundException, BadRequestException {
		if ( conteneur.getDateArrivee() == null ) {
			conteneur.setDateArrivee( DateStringConverter.stringToDate( date ) );	
			if ( !( conteneur.getState() instanceof EnRoute ) ) {
				throw new BadRequestException("Conteneur toujours en chargement");
			}
			if ( conteneur.getDateArrivee().before( conteneur.getDateDepart() ) ) {
				throw new BadRequestException("Date < Date Depart");
			}	
			conteneur.getState().arrive();
			return saveConteneur(conteneur);
		} else {
			throw new BadRequestException("Conteneur deja Arrivé");
		}
	}
	
	public Conteneur dechargementConteneur( Conteneur conteneur, String date ) throws ParseException, ResourceNotFoundException, BadRequestException {
		
		if ( conteneur.getDateDechargement() == null ) {
			conteneur.setDateDechargement( DateStringConverter.stringToDate( date ) );
			if ( !( conteneur.getState() instanceof Arrive ) ) {
				throw new BadRequestException("Recevoir d'abord le conteneur");
			}
			if ( conteneur.getDateDechargement().before( conteneur.getDateArrivee() ) ) {
				throw new BadRequestException("Date < Date Arrivee");
			}
			conteneur.getState().decharger();	
			return saveConteneur(conteneur);
		} else {
			throw new BadRequestException("Conteneur deja Dechargé");
		}
	}

}
