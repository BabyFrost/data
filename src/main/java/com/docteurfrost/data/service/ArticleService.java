package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.model.article.ArticleState;
import com.docteurfrost.data.model.categorie.Categorie;
import com.docteurfrost.data.model.conteneur.Conteneur;
import com.docteurfrost.data.repository.ArticleRepository;

@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	public Article getArticleById( String id ) throws ResourceNotFoundException {	
		return articleRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("No such Panier !") );
	}
	
	public List<Article> getAllArticle() throws ResourceNotFoundException {	
		List<Article> articles = new ArrayList<>();	
		articleRepository.findAll().forEach(articles::add);	
		return articles;
	}
	
	public List<Article> getAllArticleByCategorie( Categorie categorie ) throws ResourceNotFoundException {	
		List<Article> articles = new ArrayList<>();	
		articleRepository.findAllByCategorie( categorie ).forEach( articles::add );
		return articles;
	}
	
	public List<Article> getAllArticleByConteneur( Conteneur conteneur ) throws ResourceNotFoundException {	
		List<Article> articles = new ArrayList<>();	
		articleRepository.findAllByConteneur( conteneur ).forEach( articles::add );
		return articles;
	}
	
	public List<Article> getAllArticleByState( ArticleState state ) throws ResourceNotFoundException {	
		List<Article> articles = new ArrayList<>();	
		articleRepository.findAllByState( state ).forEach(articles::add);
		return articles;
	}
	
	public List<Article> getAllArticleByCategorieAndConteneur( Categorie categorie, Conteneur conteneur ) throws ResourceNotFoundException {	
		List<Article> articles = new ArrayList<>();	
		articleRepository.findAllByCategorieAndConteneur( categorie, conteneur ).forEach( articles::add );
		return articles;
	}
	
	public Article saveArticle( Article article ) {
		return articleRepository.save(article);
	}
	
	public List<Article> saveAllArticle( List<Article> articles ) {
		return (List<Article>) articleRepository.saveAll(articles);
	}
	
	public Article createArticle( Article article ) throws ResourceConflictException {		
		Optional<Article> articleTmp = articleRepository.findById( article.getId() );
		if ( articleTmp.isPresent() ) { throw new ResourceConflictException("Article existe deja"); }
		return saveArticle( article );
	}
	
	public Article updateArticle( Article article ) throws ResourceNotFoundException {
		getArticleById( article.getId() );
		return saveArticle( article );
	}
	
	public void deleteArticle( Article article ) throws ResourceNotFoundException {
		articleRepository.delete( article );
	}
	
	public void deleteArticleById( String idArticle ) throws ResourceNotFoundException {
		deleteArticle( getArticleById( idArticle ) );
	}
	
	@Transactional
	public List<Article> createAllArticle( List<Article> articles ) throws ResourceConflictException {
		for(int i = 0; i < articles.size(); i++ ) {
			createArticle( articles.get(i) );
		}
		return articles;
	}
	
	@Transactional
	public List<Article> updateAllArticle( List<Article> articles ) throws ResourceNotFoundException {
		for(int i = 0; i < articles.size(); i++ ) {
			updateArticle( articles.get(i) );
		}
		return articles;
	}
	
}
