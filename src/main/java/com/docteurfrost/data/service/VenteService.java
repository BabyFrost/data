package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Vente;
import com.docteurfrost.data.model.article.Article;
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
	
	public List<Vente> getAllVenteByArticle( Article article ) throws ResourceNotFoundException {	
		List<Vente> ventes = new ArrayList<>();	
		venteRepository.findAllByArticle(article).forEach(ventes::add);	
		return ventes;
	}
	
	public Vente saveVente( Vente vente ) {
		return venteRepository.save(vente);
	}
	
	public List<Vente> saveAllVente( List<Vente> ventes ) {
		return (List<Vente>) venteRepository.saveAll(ventes);
	}
	
	public Vente createVente( Vente vente ) throws ResourceConflictException {
		System.out.println( "Dans Create Vente" );
		Optional<Vente> venteTmp = venteRepository.findById( vente.getId() );
		if ( venteTmp.isPresent() ) { throw new ResourceConflictException("Vente existe deja"); }
		System.out.println( "Sauvegarde Vente" );
		return saveVente( vente );
	}
	
	public Vente updateVente( Vente vente ) throws ResourceNotFoundException {
		getVenteById( vente.getId() );
		return saveVente( vente );
	}
	
	@Transactional
	public List<Vente> createAllVente( List<Vente> ventes ) throws ResourceConflictException {
		System.out.println("Create All Ventes");
		System.out.println( ventes.size() );
		for(int i = 0; i < ventes.size(); i++ ) {
			System.out.println( i );
			System.out.println( ventes.get(i).getArticle().getId() );
			System.out.println( ventes.get(i).getId() );
			createVente( ventes.get(i) );
		}
		return ventes;
	}
	
	@Transactional
	public List<Vente> updateAllVente( List<Vente> ventes ) throws ResourceNotFoundException {
		for(int i = 0; i < ventes.size(); i++ ) {
			updateVente( ventes.get(i) );
		}
		return ventes;
	}

}
