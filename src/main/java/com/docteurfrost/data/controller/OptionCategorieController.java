package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
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
	public List<OptionCategorieDTO> getAllOptionCategorieDTO( ) throws ResourceNotFoundException {
		
		List<OptionCategorie> optionsCategorie = optionCategorieService.getAllOptionCategorie();
		List<OptionCategorieDTO> optionsCategorieDTO = new ArrayList<>();
		for (int i=0; i<optionsCategorie.size(); i++) {
			optionsCategorieDTO.add( new OptionCategorieDTO( optionsCategorie.get(i) ) );
		}
		
		return optionsCategorieDTO;
	}
	
	@PostMapping()
	@ResponseBody
	public OptionCategorie createOptionCategoryDTO(@RequestBody OptionCategorieDTO optionCategorieDTO) throws ResourceConflictException, ResourceNotFoundException {
		
		Categorie categorie = categorieService.getCategorieById( optionCategorieDTO.getCategorie() );
		OptionCategorie optionCategorie = new OptionCategorie( categorie, optionCategorieDTO.getNom(), optionCategorieDTO.getLibelle(), optionCategorieDTO.getIsNumerique(), optionCategorieDTO.getIsFree(), optionCategorieDTO.getIsBoolean() );
		optionCategorie = optionCategorieService.createOptionCategory(optionCategorie);
		if ( optionCategorie.getIsBoolean() ) {
			ValeurOption valeurOption0 = new ValeurOption( "OUI", optionCategorie );
			ValeurOption valeurOption1 = new ValeurOption( "NON", optionCategorie );
			
			valeurOptionService.saveValeurOption( valeurOption0 );
			valeurOptionService.saveValeurOption( valeurOption1 );
		}
		return optionCategorie;

	}
}