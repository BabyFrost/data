package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Marque;
import com.docteurfrost.data.service.CategorieService;
import com.docteurfrost.data.service.MarqueService;

@RequestMapping("/marques")
@RestController
public class MarqueController {

	@Autowired
	private CategorieService categorieService;
	
	@Autowired
	private MarqueService marqueService;
	
	@GetMapping()
	@ResponseBody
	public List<MarqueDTO> getAllContainersDTO( ) throws ResourceNotFoundException {	
		
		List<Marque> marques = marqueService.getAllMarque();
		List<MarqueDTO> marquesDTO = new ArrayList<>();
		for (int i=0; i<marques.size(); i++) {
			marquesDTO.add( new MarqueDTO( marques.get(i) ) );
		}
		
		return marquesDTO;
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<MarqueDTO> saveMarqueDTO(@RequestBody MarqueDTO marqueDTO) throws ResourceConflictException {
		Marque marque = new Marque( marqueDTO.getNom(), marqueDTO.getLibelle() );
		MarqueDTO responseMarqueDTO = new MarqueDTO( marqueService.createMarque( marque ) );
		return new ResponseEntity<>( responseMarqueDTO, HttpStatus.CREATED );
	}
	
	@PutMapping()
	@ResponseBody
	public ResponseEntity<MarqueDTO> updateMarqueDTO(@RequestBody MarqueDTO marqueDTO) throws ResourceNotFoundException {
		Marque marque = new Marque( marqueDTO.getNom(), marqueDTO.getLibelle() );
		MarqueDTO responseMarqueDTO = new MarqueDTO( marqueService.updateMarque(marque) );
		return new ResponseEntity<>( responseMarqueDTO, HttpStatus.OK );
	}
	
	@PatchMapping("/{idMarque}/addCategorie")
	@ResponseBody
	public ResponseEntity<MarqueDTO> addCategoriesMarque( @PathVariable("idMarque") String idMarque, @RequestBody List<CategorieDTO> categoriesDTO ) throws ResourceNotFoundException {
		
		List<Categorie> categories = new ArrayList<>();
		for (int i = 0; i<categoriesDTO.size(); i++) {		
			Categorie categorie = categorieService.getCategorieById( categoriesDTO.get(i).getNom() );	
			categories.add(categorie);
		}
		
		Marque marque = marqueService.getMarqueById(idMarque);
		for (int i = 0; i<categories.size(); i++) {
			Categorie categorie = categories.get(i);
			marque.getCategories().add(categorie);
		}
		
		MarqueDTO responseMarqueDTO = new MarqueDTO( marqueService.updateMarque( marque ) );
		return new ResponseEntity<>( responseMarqueDTO, HttpStatus.OK );
	}
	
}
