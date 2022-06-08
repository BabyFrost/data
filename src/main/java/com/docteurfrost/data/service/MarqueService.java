package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Marque;
import com.docteurfrost.data.repository.MarqueRepository;

@Service
public class MarqueService {
	
	@Autowired
	private MarqueRepository marqueRepository;
	
	public Marque getMarqueById( String id ) throws ResourceNotFoundException {	
		return marqueRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No such Marque !") );
	}
	
	public List<Marque> getAllMarque() throws ResourceNotFoundException {	
		List<Marque> marques = new ArrayList<>();	
		marqueRepository.findAll().forEach(marques::add);	
		return marques;
	}
	
	public Marque saveMarque( Marque marque ) {
		return marqueRepository.save(marque);
	}
	
	public Marque createMarque( Marque marque ) throws ResourceConflictException {	
		Optional<Marque> marqueTmp = marqueRepository.findById( marque.getNom() );
		if ( marqueTmp.isPresent() ) { throw new ResourceConflictException("Marque existe deja"); }
		return saveMarque( marque );
	}
	
	public Marque updateMarque( Marque marque ) throws ResourceNotFoundException {
		getMarqueById( marque.getNom() );
		return saveMarque( marque );
	}

}
