package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.categorie.OptionCategorie;
import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.repository.OptionCategorieRepository;

@Service
public class OptionCategorieService {
	
	@Autowired
	private OptionCategorieRepository optionCategorieRepository;
	
	public OptionCategorie getOptionCategorieById( String id ) throws ResourceNotFoundException {	
		return optionCategorieRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No such Panier !") );
	}
	
	public List<OptionCategorie> getAllOptionCategorie(  ) throws ResourceNotFoundException {	
		List<OptionCategorie> optionsCategorie = new ArrayList<>();
		optionCategorieRepository.findAll().forEach(optionsCategorie::add);
		return optionsCategorie;
	}
	
	public OptionCategorie saveOptionCategory( OptionCategorie optionCategorie) {
		return optionCategorieRepository.save(optionCategorie);
	}
	
	public OptionCategorie createOptionCategory( OptionCategorie optionCategorie ) throws ResourceConflictException {
		
		Optional<OptionCategorie> optionCategorieTmp = optionCategorieRepository.findById( optionCategorie.getId() );
		if ( optionCategorieTmp.isPresent() ) { throw new ResourceConflictException("Option Categorie existe deja"); }
		
		return saveOptionCategory( optionCategorie );
	}
}
