package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Avance;
import com.docteurfrost.data.repository.AvanceRepository;

@Service
public class AvanceService {
	
	@Autowired
	private AvanceRepository avanceRepository;
	
	public Avance getAvanceById( int id ) throws ResourceNotFoundException {	
		return avanceRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No such Avance !") );
	}
	
	public List<Avance> getAllAvance() throws ResourceNotFoundException {	
		List<Avance> avances = new ArrayList<>();	
		avanceRepository.findAll().forEach(avances::add);	
		return avances;
	}
	
	public Avance saveAvance( Avance avance ) {
		return avanceRepository.save(avance);
	}
	
	public Avance createAvance( Avance avance ) throws ResourceConflictException {
		
		Optional<Avance> avanceTmp = avanceRepository.findById( avance.getId() );
		if ( avanceTmp.isPresent() ) { throw new ResourceConflictException("Avance existe deja"); }
		return saveAvance( avance );
	}

}
