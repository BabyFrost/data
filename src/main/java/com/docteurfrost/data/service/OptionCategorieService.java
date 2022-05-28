package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.categorie.Categorie;
import com.docteurfrost.data.categorie.OptionCategorie;
import com.docteurfrost.data.dto.OptionCategorieDTO;
import com.docteurfrost.data.repository.OptionCategorieRepository;

@Service
public class OptionCategorieService {
	
	@Autowired
	private OptionCategorieRepository optionCategorieRepository;
	
	public OptionCategorie getOptionCategorie( String id ) {
		
		Optional<OptionCategorie> optionCategorieTmp = optionCategorieRepository.findById( id.toUpperCase() );
		if ( optionCategorieTmp.isPresent() ) {
			return optionCategorieTmp.get();
		} else {
			return null;
		}
	
	}
	
	public List<OptionCategorie> getAllOptionsCategorie( ) {
		
		List<OptionCategorie> optionsCategorie = new ArrayList<>();
		optionCategorieRepository.findAll().forEach(optionsCategorie::add);
		
		return optionsCategorie;
	}
	
	public List<OptionCategorieDTO> getAllOptionsCategorieDTO( ) {
		
		List<OptionCategorie> optionsCategorie = getAllOptionsCategorie();
		
		List<OptionCategorieDTO> optionsCategorieDTO = new ArrayList<>();
		for (int i=0; i<optionsCategorie.size(); i++) {
			optionsCategorieDTO.add( new OptionCategorieDTO( optionsCategorie.get(i) ) );
		}
		
		return optionsCategorieDTO;
	}
	
	public OptionCategorie saveOptionCategory( OptionCategorie optionCategorie) {
		return optionCategorieRepository.save(optionCategorie);
	}
	
	//TODO
	//THROW AN EXCEPTION IF ELEMENT EXISTS
	public OptionCategorie saveOptionCategoryDTO( OptionCategorieDTO optionCategorieDTO, Categorie categorie) {
		
		OptionCategorie optionCategorie = new OptionCategorie( categorie, optionCategorieDTO.getNom(), optionCategorieDTO.getLibelle(), optionCategorieDTO.getIsNumerique(), optionCategorieDTO.getIsFree(), optionCategorieDTO.getIsBoolean()  );
		
		//Write Exception to return
		if ( optionCategorieDTO.getIsBoolean() && optionCategorieDTO.getIsFree() ) {
//			return new ResponseEntity<>( "Boolean cannot be free", HttpStatus.BAD_REQUEST );
			return null;
		}
		return saveOptionCategory(optionCategorie);
	}
}
