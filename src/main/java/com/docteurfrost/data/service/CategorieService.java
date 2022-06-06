package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.categorie.Categorie;
import com.docteurfrost.data.dto.CategorieDTO;
import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.repository.CategorieRepository;

@Service
public class CategorieService {

	@Autowired
	private CategorieRepository categorieRepository;
	
	public Categorie getCategorieById( String id ) throws ResourceNotFoundException {	
		return categorieRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No such Panier !") );
	}
	
	public List<Categorie> getAllCategorie() throws ResourceNotFoundException {
		
		List<Categorie> categories = new ArrayList<>();	
		categorieRepository.findAll().forEach(categories::add);
		
		return categories;
	}
	
	public Categorie saveCategorie( Categorie categorie) {
		return categorieRepository.save(categorie);
	}
	
	public Categorie createCategorie( Categorie categorie ) throws ResourceConflictException {
		
		Optional<Categorie> categorieTmp = categorieRepository.findById( categorie.getNom() );
		if ( categorieTmp.isPresent() ) { throw new ResourceConflictException("Client existe deja"); }
		
		return saveCategorie( categorie );
	}
	
	public ResponseEntity<String> createCategoryDTO( CategorieDTO categorieDTO) {
		if ( categorieRepository.findById(categorieDTO.getNom()).isPresent() ) {
			return new ResponseEntity<>( "Cet Categorie existe deja", HttpStatus.CONFLICT );
		}
		
		Categorie categorie = new Categorie( categorieDTO.getNom(), categorieDTO.getLibelle() );
		saveCategorie( categorie );
		
		return new ResponseEntity<>( "Categorie cree", HttpStatus.CREATED );
	}
	
}
