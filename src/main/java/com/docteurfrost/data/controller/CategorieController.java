package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.categorie.Categorie;
import com.docteurfrost.data.dto.CategorieDTO;
import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.service.CategorieService;

@RequestMapping("/categories")
@RestController
public class CategorieController {

	@Autowired
	private CategorieService categorieService;
	
	@GetMapping()
	@ResponseBody
	public List<CategorieDTO> findAllCategoriesDTO( ) throws ResourceNotFoundException {	
		List<Categorie> categories = categorieService.getAllCategorie();
		List<CategorieDTO> categoriesDTO = new ArrayList<>();
		for (int i=0; i<categories.size(); i++) {
			categoriesDTO.add( new CategorieDTO( categories.get(i) ) );
		}		
		return categoriesDTO;	
	}
	
	@GetMapping("/{nomCategorie}")
	@ResponseBody
	public CategorieDTO findCategorieDTO( @PathVariable("nomCategorie") String nomCategorie ) throws ResourceNotFoundException {	
		return new CategorieDTO( categorieService.getCategorieById(nomCategorie) ) ;	
	}
	
	@PostMapping()
	@ResponseBody
	public Categorie createCategorieDTO(@RequestBody CategorieDTO categorieDTO) throws ResourceConflictException {	
		Categorie categorie = new Categorie( categorieDTO.getNom(), categorieDTO.getLibelle() );
		return categorieService.createCategorie( categorie );
	}
}
