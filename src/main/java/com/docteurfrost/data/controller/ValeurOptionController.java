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

import com.docteurfrost.data.categorie.OptionCategorie;
import com.docteurfrost.data.categorie.ValeurOption;
import com.docteurfrost.data.dto.ValeurOptionDTO;
import com.docteurfrost.data.repository.OptionCategorieRepository;
import com.docteurfrost.data.repository.ValeurOptionRepository;

@RequestMapping("/valeurs_options")
@RestController
public class ValeurOptionController {

	@Autowired
	private ValeurOptionRepository valeurOptionRepository;
	
	@Autowired
	private OptionCategorieRepository optionCategorieRepository;
	
	@GetMapping()
	@ResponseBody
	public Iterable<ValeurOptionDTO> getAllOptions( ) {
		
		List<ValeurOption> valeursOption = new ArrayList<>();
		valeurOptionRepository.findAll().forEach(valeursOption::add);
		
		List<ValeurOptionDTO> ValeursOptionDTO = new ArrayList<>();
		for (int i=0; i<valeursOption.size(); i++) {
			ValeursOptionDTO.add( new ValeurOptionDTO( valeursOption.get(i) ) );
		}
		
		return ValeursOptionDTO;
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<String> saveCategory(@RequestBody ValeurOptionDTO valeurOptionDTO) {
		if ( valeurOptionRepository.findById( valeurOptionDTO.getOption().toUpperCase()+"_"+valeurOptionDTO.getValeur().toUpperCase() ).isPresent() ) {
			return new ResponseEntity<>( "Cette Valeur d'option existe deja", HttpStatus.CONFLICT );
		}
		
		Optional<OptionCategorie> optionCategorieTmp = optionCategorieRepository.findById( valeurOptionDTO.getOption() );
		OptionCategorie optionCategorie;
		if ( optionCategorieTmp.isPresent() ) {
			optionCategorie = optionCategorieTmp.get();
		} else {
			return new ResponseEntity<>( " Option inexistante ", HttpStatus.BAD_REQUEST );
		}
		
		if ( optionCategorie.getIsNumerique() ) {
			if ( !valeurOptionDTO.getValeur().matches("[0-9]+") ) {
				return new ResponseEntity<>( " Valeur non numerique ", HttpStatus.BAD_REQUEST );
			}
		}
		
		ValeurOption valeurOption = new ValeurOption( valeurOptionDTO.getValeur(), optionCategorie );
		valeurOptionRepository.save( valeurOption );
		
		return new ResponseEntity<>( "Valeur d'option ajoutee", HttpStatus.CREATED );
	}
	
}
