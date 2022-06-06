package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Vente;
import com.docteurfrost.data.repository.VenteRepository;

@Service
public class VenteService {
	
	@Autowired
	private VenteRepository venteRepository;
	
	public Vente getVenteById( int id ) throws ResourceNotFoundException {	
		return venteRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No such User !") );
	}
	
	public List<Vente> getAllVente() throws ResourceNotFoundException {	
		List<Vente> ventes = new ArrayList<>();	
		venteRepository.findAll().forEach(ventes::add);	
		return ventes;
	}
	
	public Vente saveVente( Vente vente ) {
		return venteRepository.save(vente);
	}
	
	public Vente createVente( Vente vente ) throws ResourceConflictException {		
		Optional<Vente> venteTmp = venteRepository.findById( vente.getId() );
		if ( venteTmp.isPresent() ) { throw new ResourceConflictException("Client existe deja"); }	
		return saveVente( vente );
	}

}
