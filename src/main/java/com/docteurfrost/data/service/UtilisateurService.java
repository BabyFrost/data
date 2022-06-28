package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Utilisateur;
import com.docteurfrost.data.repository.UtilisateurRepository;

@Service
public class UtilisateurService {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	public Utilisateur getUtilisateurById( int id ) throws ResourceNotFoundException {	
		return utilisateurRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No such User !") );
	}
	
	public Utilisateur getUtilisateurByUsername( String username ) throws ResourceNotFoundException {	
		return utilisateurRepository.findByUsername(username).orElseThrow( () -> new ResourceNotFoundException("No such User !") );
	}
	
	public List<Utilisateur> getAllUtilisateur() throws ResourceNotFoundException {	
		List<Utilisateur> utilisateurs = new ArrayList<>();	
		utilisateurRepository.findAll().forEach(utilisateurs::add);	
		return utilisateurs;
	}
	
	public Utilisateur saveUtilisateur( Utilisateur utilisateurs ) {
		return utilisateurRepository.save(utilisateurs);
	}
	
	public Utilisateur createUtilisateur( Utilisateur utilisateur ) throws ResourceConflictException {		
		Optional<Utilisateur>utilisateurTmp = utilisateurRepository.findById( utilisateur.getId() );
		if ( utilisateurTmp.isPresent() ) { throw new ResourceConflictException("Client existe deja"); }	
		return saveUtilisateur( utilisateur );
	}

}
