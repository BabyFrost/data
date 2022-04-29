package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.dto.CategorieDTO;
import com.docteurfrost.data.model.Categorie;
import com.docteurfrost.data.repository.CategorieRepository;

@RequestMapping("/categories")
@RestController
public class CategorieController {

	@Autowired
	private CategorieRepository categorieRepository;
	
	@GetMapping()
	@ResponseBody
	public Iterable<CategorieDTO> getAllCategories( ) {
		
		List<Categorie> categories = new ArrayList<>();
		categorieRepository.findAll().forEach(categories::add);
		
		List<CategorieDTO> categoriesDTO = new ArrayList<>();
		for (int i=0; i<categories.size(); i++) {
			categoriesDTO.add( new CategorieDTO( categories.get(i) ) );
		}
		
		return categoriesDTO;
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<String> saveCategory(@RequestBody CategorieDTO categorieDTO) {
		if ( categorieRepository.findByNom(categorieDTO.getNom()).isPresent() ) {
			return new ResponseEntity<>( "Cet Categorie existe deja", HttpStatus.CONFLICT );
		}
		
		Categorie categorie = new Categorie( categorieDTO.getNom(), categorieDTO.getLibelle() );
		categorieRepository.save(categorie);
		
		return new ResponseEntity<>( "Categorie cree", HttpStatus.CREATED );
	}
}