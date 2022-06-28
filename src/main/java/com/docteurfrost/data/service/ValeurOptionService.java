package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.dto.ValeurOptionDTO;
import com.docteurfrost.data.model.categorie.OptionCategorie;
import com.docteurfrost.data.model.categorie.ValeurOption;
import com.docteurfrost.data.repository.ValeurOptionRepository;

@Service
public class ValeurOptionService {

	@Autowired
	private ValeurOptionRepository valeurOptionRepository;
	
	public List<ValeurOption> getAllOptions( ) {
		
		List<ValeurOption> valeursOption = new ArrayList<>();
		valeurOptionRepository.findAll().forEach(valeursOption::add);
		
		return valeursOption;
	}
	
	public List<ValeurOptionDTO> getAllOptionsDTO( ) {
		
		List<ValeurOption> valeursOption = getAllOptions();
		
		List<ValeurOptionDTO> valeursOptionDTO = new ArrayList<>();
		for (int i=0; i<valeursOption.size(); i++) {
			valeursOptionDTO.add( new ValeurOptionDTO( valeursOption.get(i) ) );
		}
		
		return valeursOptionDTO;
	}
	
	public void saveValeurOption( ValeurOption valeurOption) {
		valeurOptionRepository.save( valeurOption );
	}
	
	public ResponseEntity<String> saveValeurOptionDTO( ValeurOptionDTO valeurOptionDTO, OptionCategorie optionCategorie) {
		if ( valeurOptionRepository.findById( valeurOptionDTO.getOption().toUpperCase()+"_"+valeurOptionDTO.getValeur().toUpperCase() ).isPresent() ) {
			return new ResponseEntity<>( "Cette Valeur d'option existe deja", HttpStatus.CONFLICT );
		}
		
		if ( optionCategorie.getIsNumerique() ) {
			if ( !valeurOptionDTO.getValeur().matches("[0-9]+") ) {
				return new ResponseEntity<>( " Valeur non numerique ", HttpStatus.BAD_REQUEST );
			}
		}
		
		ValeurOption valeurOption = new ValeurOption( valeurOptionDTO.getValeur(), optionCategorie );
		saveValeurOption( valeurOption );
		
		return new ResponseEntity<>( "Valeur d'option ajoutee", HttpStatus.CREATED );
	}
	
}
