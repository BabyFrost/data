package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	public Article getArticleById( String id ) throws ResourceNotFoundException {	
		return articleRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No such Panier !") );
	}
	
	public List<Article> getAllArticles() throws ResourceNotFoundException {	
		List<Article> articles = new ArrayList<>();	
		articleRepository.findAll().forEach(articles::add);	
		return articles;
	}
	
	public Article saveArticle( Article article ) {
		return articleRepository.save(article);
	}
	
	public Article createArticle( Article article ) throws ResourceConflictException {		
		Optional<Article> articleTmp = articleRepository.findById( article.getId() );
		if ( articleTmp.isPresent() ) { throw new ResourceConflictException("Article existe deja"); }
		return saveArticle( article );
	}
	
}
