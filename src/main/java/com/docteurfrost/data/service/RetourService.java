package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Retour;
import com.docteurfrost.data.repository.RetourRepository;

@Service
public class RetourService {

	@Autowired
	private RetourRepository retourRepository;
	
	public Retour getRetourById( int id ) throws ResourceNotFoundException {	
		return retourRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No such Panier !") );
	}
	
	public List<Retour> getAllRetours() throws ResourceNotFoundException {	
		List<Retour> retours = new ArrayList<>();	
		retourRepository.findAll().forEach(retours::add);	
		return retours;
	}
	
	public Retour saveRetour( Retour retour ) {
		return retourRepository.save(retour);
	}
	
	public Retour createRetour( Retour retour ) throws ResourceConflictException {	
		Optional<Retour> retourTmp = retourRepository.findById( retour.getId() );
		if ( retourTmp.isPresent() ) { throw new ResourceConflictException("Retour existe deja"); }	
		return saveRetour( retour );
	}
	
}
