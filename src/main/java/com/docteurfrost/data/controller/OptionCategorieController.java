package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import com.docteurfrost.data.repository.CategorieRepository;
import com.docteurfrost.data.repository.OptionCategorieRepository;
import com.docteurfrost.data.repository.ValeurOptionRepository;

@RequestMapping("/options")
@RestController
public class OptionCategorieController {

	@Autowired
	private OptionCategorieRepository optionCategorieRepository;
	
	@Autowired
	private CategorieRepository CategorieRepository;
	
	@Autowired
	ValeurOptionRepository valeurOptionRepository;
	
	@GetMapping()
	@ResponseBody
	public Iterable<OptionCategorieDTO> getAllOptions( ) {
		
		List<OptionCategorie> optionsCategorie = new ArrayList<>();
		optionCategorieRepository.findAll().forEach(optionsCategorie::add);
		
		List<OptionCategorieDTO> optionsCategorieDTO = new ArrayList<>();
		for (int i=0; i<optionsCategorie.size(); i++) {
			optionsCategorieDTO.add( new OptionCategorieDTO( optionsCategorie.get(i) ) );
		}
		
		return optionsCategorieDTO;
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<String> saveCategory(@RequestBody OptionCategorieDTO optionCategorieDTO) {
		if ( optionCategorieRepository.findById( optionCategorieDTO.getId().toUpperCase() ).isPresent() ) {
			return new ResponseEntity<>( "Cette Option existe deja", HttpStatus.CONFLICT );
		}
		
		Optional<Categorie> categorieTmp = CategorieRepository.findById( optionCategorieDTO.getCategorie() );
		Categorie categorie;
		if ( categorieTmp.isPresent() ) {
			categorie = categorieTmp.get();
		} else {
			return new ResponseEntity<>( " Categorie inexistante ", HttpStatus.BAD_REQUEST );
		}
		
		
		
		OptionCategorie optionCategorie = new OptionCategorie( categorie, optionCategorieDTO.getNom(), optionCategorieDTO.getLibelle(), optionCategorieDTO.getIsNumerique(), optionCategorieDTO.getIsFree(), optionCategorieDTO.getIsBoolean()  );
		
		if ( optionCategorieDTO.getIsBoolean() ) {
			
			if ( optionCategorieDTO.getIsFree() ) {
				return new ResponseEntity<>( "Boolean cannot be free", HttpStatus.BAD_REQUEST );	
			}
			
			ValeurOption valeurOption0 = new ValeurOption( "OUI", optionCategorie );
			ValeurOption valeurOption1 = new ValeurOption( "NON", optionCategorie );
			
			optionCategorieRepository.save( optionCategorie );
			valeurOptionRepository.save( valeurOption0 );
			valeurOptionRepository.save( valeurOption1 );
			return new ResponseEntity<>( "Option cree", HttpStatus.CREATED );
		} else {
			
			optionCategorieRepository.save( optionCategorie );
			return new ResponseEntity<>( "Option cree", HttpStatus.CREATED );
			
		}

	}
}