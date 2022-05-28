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

import com.docteurfrost.data.categorie.OptionCategorie;
import com.docteurfrost.data.dto.ValeurOptionDTO;
import com.docteurfrost.data.service.OptionCategorieService;
import com.docteurfrost.data.service.ValeurOptionService;

@RequestMapping("/valeurs_options")
@RestController
public class ValeurOptionController {

	@Autowired
	private ValeurOptionService valeurOptionService;
	
	@Autowired
	private OptionCategorieService optionCategorieService;
	
	@GetMapping()
	@ResponseBody
	public List<ValeurOptionDTO> getAllValeursOption( ) {
		return valeurOptionService.getAllOptionsDTO();
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<String> saveValeurOption(@RequestBody ValeurOptionDTO valeurOptionDTO) {
		
		OptionCategorie optionCategorie = optionCategorieService.getOptionCategorie( valeurOptionDTO.getOption() );
		valeurOptionService.saveValeurOptionDTO( valeurOptionDTO, optionCategorie );
		
		return new ResponseEntity<>( "Valeur d'option ajoutee", HttpStatus.CREATED );
	}
	
}
