package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.categorie.IdOptionArticle;
import com.docteurfrost.data.categorie.OptionArticle;
import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.repository.OptionArticleRepository;

@Service
public class OptionArticleService {
	
	@Autowired
	private OptionArticleRepository optionArticleRepository;
	
	public OptionArticle getOptionArticleById( IdOptionArticle id ) throws ResourceNotFoundException {	
		return optionArticleRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No such Option Article !") );
	}
	
	public List<OptionArticle> getAllOptionArticle() throws ResourceNotFoundException {	
		List<OptionArticle> optionsArticle = new ArrayList<>();	
		optionArticleRepository.findAll().forEach(optionsArticle::add);	
		return optionsArticle;
	}
	
	public OptionArticle saveOptionArticle( OptionArticle optionArticle ) {
		return optionArticleRepository.save(optionArticle);
	}
	
	public List<OptionArticle> saveAllOptionArticle( List<OptionArticle> optionsArticle ) {
		return (List<OptionArticle>) optionArticleRepository.saveAll(optionsArticle);
	}
	
	public OptionArticle createOptionArticle( OptionArticle optionArticle ) throws ResourceConflictException {
		Optional<OptionArticle> optionArticleTmp = optionArticleRepository.findById( optionArticle.getId() );
		if ( optionArticleTmp.isPresent() ) { throw new ResourceConflictException("Option Article existe deja"); }
		return saveOptionArticle( optionArticle );
	}
	
	@Transactional
	public List<OptionArticle> createAllOptionArticle( List<OptionArticle> optionsArticle ) throws ResourceConflictException {
		for(int i = 0; i < optionsArticle.size(); i++ ) {
			createOptionArticle( optionsArticle.get(i) );
		}
		return optionsArticle;
	}
	
	public void deleteOptionArticle( OptionArticle optionArticle ) throws ResourceNotFoundException {
		getOptionArticleById( optionArticle.getId() );
		optionArticleRepository.delete(optionArticle);
	}
	
}
