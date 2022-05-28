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
import com.docteurfrost.data.repository.CategorieRepository;

@Service
public class CategorieService {

	@Autowired
	private CategorieRepository categorieRepository;
	
	public Categorie findCategorie( String nomCategorie ) {
		
		Optional<Categorie> categorieTmp = categorieRepository.findById(nomCategorie);  
		if ( categorieTmp.isPresent() ) {
			return categorieTmp.get();
		} else {
			return null;
		}
		
	}
	
	public List<Categorie> findAllCategories() {
		
		List<Categorie> categories = new ArrayList<>();
		categorieRepository.findAll().forEach(categories::add);
		
		return categories;
		
	}
	
	public void saveCategory( Categorie categorie) {
		categorieRepository.save(categorie);
	}
	
	public ResponseEntity<String> createCategoryDTO( CategorieDTO categorieDTO) {
		if ( categorieRepository.findById(categorieDTO.getNom()).isPresent() ) {
			return new ResponseEntity<>( "Cet Categorie existe deja", HttpStatus.CONFLICT );
		}
		
		Categorie categorie = new Categorie( categorieDTO.getNom(), categorieDTO.getLibelle() );
		saveCategory( categorie );
		
		return new ResponseEntity<>( "Categorie cree", HttpStatus.CREATED );
	}
	
}
