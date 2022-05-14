package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.categorie.Categorie;
import com.docteurfrost.data.dto.CategorieDTO;
import com.docteurfrost.data.dto.MarqueDTO;
import com.docteurfrost.data.model.Marque;
import com.docteurfrost.data.repository.CategorieRepository;
import com.docteurfrost.data.repository.MarqueRepository;

@RequestMapping("/marques")
@RestController
public class MarqueController {

	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private MarqueRepository marqueRepository;
	
	@GetMapping()
	@ResponseBody
	public Iterable<MarqueDTO> getAllContainers( ) {
		List<Marque> marques = new ArrayList<>();
		marqueRepository.findAll().forEach(marques::add);
		
		List<MarqueDTO> marquesDTO = new ArrayList<>();
		for (int i=0; i<marques.size(); i++) {
			marquesDTO.add( new MarqueDTO( marques.get(i) ) );
		}
		
		return marquesDTO;
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<String> saveMarque(@RequestBody MarqueDTO marqueDTO) {
		if ( marqueRepository.findById(marqueDTO.getNom()).isPresent() ) {
			return new ResponseEntity<>( "Cette Marque existe deja", HttpStatus.CONFLICT );
		}
		
		Marque marque = new Marque( marqueDTO.getNom(), marqueDTO.getLibelle() );
		marqueRepository.save(marque);
		
		return new ResponseEntity<>( "Marque cree", HttpStatus.CREATED );
	}
	
	@PutMapping()
	@ResponseBody
	public ResponseEntity<String> updateMarque(@RequestBody MarqueDTO marqueDTO) {
		if ( marqueRepository.findById(marqueDTO.getNom()).isPresent() ) {
			Marque marque = marqueRepository.findById(marqueDTO.getNom()).get();
			marque.setNom( marqueDTO.getNom() );
			marque.setLibelle( marqueDTO.getLibelle() );
			marqueRepository.save(marque);
			
			return new ResponseEntity<>( "Marque Editee", HttpStatus.OK );
		} else {
			return new ResponseEntity<>( "Cette marque n'existe pas", HttpStatus.BAD_REQUEST );
		}	
		
	}
	
	@PatchMapping("/categorie/{idMarque}")
	@ResponseBody
	public ResponseEntity<String> updateMarque( @PathVariable("idMarque") String idMarque, @RequestBody List<CategorieDTO> categoriesDTO ) {
		
		Optional<Marque> marqueTmp = marqueRepository.findById( idMarque );
		Marque marque;
		if ( marqueTmp.isPresent() ) {
			marque = marqueTmp.get();
		} else {
			return new ResponseEntity<>( "Marque Inexistante", HttpStatus.ALREADY_REPORTED );
		}

		for (int i = 0; i<categoriesDTO.size(); i++) {
			
			Categorie categorie;
			Optional<Categorie> categorieTmp = categorieRepository.findById( categoriesDTO.get(i).getNom() );  
			if ( categorieTmp.isPresent() ) {
				
				categorie = categorieTmp.get();
				if ( !( marque.getCategories().contains(categorie) ) ) {
					marque.getCategories().add(categorie);
					marqueRepository.save( marque );
				}
		
			} else {
				return new ResponseEntity<>( "Categorie inexistant", HttpStatus.BAD_REQUEST );
			}
			
		}
		
		return new ResponseEntity<>( "Categories Enregistrees", HttpStatus.OK );
		
	}
	
}
