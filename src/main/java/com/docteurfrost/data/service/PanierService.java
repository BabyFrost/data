package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Panier;
import com.docteurfrost.data.repository.PanierRepository;

@Service
public class PanierService {
	
	@Autowired
	private PanierRepository panierRepository;
	
	public Panier getPanierById( int id ) throws ResourceNotFoundException {
		return panierRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No such Panier !") );
	}
	
	public List<Panier> getAllPanier( int id ) throws ResourceNotFoundException {
		
		List<Panier> paniers = new ArrayList<>();	
		if ( id != 0 ) {
			paniers.add( getPanierById( id ) ) ;
		} else {
			panierRepository.findAll().forEach(paniers::add);
		}
		
		return paniers;
	}
	
	public Panier savePanier( Panier panier ) {
		return panierRepository.save( panier );
	}
	
	public Panier createPanier( Panier panier ) throws ResourceConflictException {
		
		Optional<Panier> panierTmp = panierRepository.findById( panier.getId() );
		if ( panierTmp.isPresent() ) { throw new ResourceConflictException("Staff already exists"); }
		
		return panierRepository.save( panier );
	}

}
