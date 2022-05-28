package com.docteurfrost.data.controller;

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

import com.docteurfrost.data.categorie.Categorie;
import com.docteurfrost.data.categorie.OptionCategorie;
import com.docteurfrost.data.categorie.ValeurOption;
import com.docteurfrost.data.dto.OptionCategorieDTO;
import com.docteurfrost.data.service.CategorieService;
import com.docteurfrost.data.service.OptionCategorieService;
import com.docteurfrost.data.service.ValeurOptionService;

@RequestMapping("/options")
@RestController
public class OptionCategorieController {

	@Autowired
	private OptionCategorieService optionCategorieService;
	
	@Autowired
	private CategorieService categorieService;
	
	@Autowired
	private ValeurOptionService valeurOptionService;
	
	@GetMapping()
	@ResponseBody
	public List<OptionCategorieDTO> getAllOptionsDTO( ) {
		return optionCategorieService.getAllOptionsCategorieDTO();
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<String> saveCategory(@RequestBody OptionCategorieDTO optionCategorieDTO) {
		
		Categorie categorie = categorieService.findCategorie( optionCategorieDTO.getCategorie() );
		OptionCategorie optionCategorie = optionCategorieService.saveOptionCategoryDTO( optionCategorieDTO, categorie);
		
		if ( optionCategorie.getIsBoolean() ) {
			ValeurOption valeurOption0 = new ValeurOption( "OUI", optionCategorie );
			ValeurOption valeurOption1 = new ValeurOption( "NON", optionCategorie );
			
			valeurOptionService.saveValeurOption( valeurOption0 );
			valeurOptionService.saveValeurOption( valeurOption1 );
		}
		
		return new ResponseEntity<>( "Option cree", HttpStatus.CREATED );

	}
}