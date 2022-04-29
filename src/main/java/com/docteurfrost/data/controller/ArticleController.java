package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.dto.ArticleDTO;
import com.docteurfrost.data.model.Categorie;
import com.docteurfrost.data.model.Conteneur;
import com.docteurfrost.data.model.OptionArticle;
import com.docteurfrost.data.model.OptionCategorie;
import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.repository.ArticleRepository;
import com.docteurfrost.data.repository.CategorieRepository;
import com.docteurfrost.data.repository.ConteneurRepository;
import com.docteurfrost.data.repository.OptionArticleRepository;
import com.docteurfrost.data.repository.OptionCategorieRepository;
import com.docteurfrost.data.tools.StringSearcher;

@RequestMapping("/articles")
@RestController
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private ConteneurRepository conteneurRepository;
	
	@Autowired
	private OptionCategorieRepository optionCategorieRepository;
	
	@Autowired
	private OptionArticleRepository optionArticleRepository;
	
	@GetMapping()
	@ResponseBody
	public Iterable<ArticleDTO> getAllArticlesDTO( ) {
		
		List<Article> articles = new ArrayList<>();
		articleRepository.findAll().forEach(articles::add);
		
		List<ArticleDTO> articlesDTO = new ArrayList<>();
		for (int i=0; i<articles.size(); i++) {
			articlesDTO.add( new ArticleDTO( articles.get(i) ) );
		}
		
		System.out.println( articlesDTO.get(0).getOptions() );
		
		return articlesDTO;
	}
	
	
	@GetMapping("/{categorie}")
	@ResponseBody 
	public Iterable<Article> getArticlesByCategorie( @PathVariable("categorie") String nomCategorie ) {
		Optional<Categorie> categorie = categorieRepository.findByNom(nomCategorie);
		  
		if ( categorie.isPresent() ) {
			return articleRepository.findAllByCategorie( categorie.get() );
		} else {
			return null;
		}
	  
	}
	
	@PostMapping()
	public ResponseEntity<String> saveArticle( @RequestBody ArticleDTO articleDTO ) {
		
		System.out.println( " Received Save Article Request" );
		
		Optional<Categorie> categorieTmp = categorieRepository.findByNom( articleDTO.getCategorie() );
		Categorie categorie;
		if ( categorieTmp.isPresent() ) {
			categorie = categorieTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez une categorie valide ", HttpStatus.BAD_REQUEST );
		}
		
		Optional<Conteneur> conteneurTmp = conteneurRepository.findByNom( articleDTO.getConteneur() );
		Conteneur conteneur;
		if ( conteneurTmp.isPresent() ) {
			conteneur = conteneurTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez un conteneur valide ", HttpStatus.BAD_REQUEST );
		}
		
		if ( articleRepository.findByNom( articleDTO.getNom()).isPresent() ) {
			return new ResponseEntity<>( "Cet article existe deja", HttpStatus.CONFLICT );
		}
		
		Article article = new Article(articleDTO.getNom(), articleDTO.getLibelle(), categorie, conteneur, articleDTO.getPrixAchat(), articleDTO.getPrix(), null );
        articleRepository.save(article);
        
        String options = articleDTO.getOptions();        
        StringSearcher searcher = new StringSearcher();
        
        List<Integer> keyIndexes = searcher.indexesOf( "{", options);
        List<Integer> keyEndIndexes = searcher.indexesOf( ":", options);
        List<Integer> valueIndexes = searcher.indexesOf( ":", options);
        List<Integer> valueEndIndexes = searcher.indexesOf( "}", options);
        
        for (int i=0; i<keyIndexes.size(); i++) {
        	
        	String option = options.substring( keyIndexes.get(i)+1, keyEndIndexes.get(i));      	
        	String valeur = options.substring( valueIndexes.get(i)+1, valueEndIndexes.get(i));      	
        	
        	Optional<OptionCategorie> optionCategorieTmp = optionCategorieRepository.findByNom( option );
        	OptionCategorie optionCategorie;
    		if ( optionCategorieTmp.isPresent() ) {
    			optionCategorie = optionCategorieTmp.get();
    		} else {
    			return new ResponseEntity<>( "Renseignez une Option valide", HttpStatus.BAD_REQUEST );
    		}	
        	
        	OptionArticle optionArticle = new OptionArticle( article, optionCategorie, valeur);
        	optionArticleRepository.save(optionArticle);
        }
        
        System.out.println( " Article saved " );
        
		return new ResponseEntity<>( "Article cree", HttpStatus.CREATED );
	}
	
	@PutMapping()
	public ResponseEntity<String> updateArticle( @RequestBody ArticleDTO articleDTO ) {
		
		System.out.println( " Received Update Request " );
		
		Optional<Article> articleTmp = articleRepository.findById( articleDTO.getId() );
		Article article;
		if ( articleTmp.isPresent() ) {
			article = articleTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez un article existant ", HttpStatus.BAD_REQUEST );
		}
		
		Optional<Categorie> categorieTmp = categorieRepository.findByNom( articleDTO.getCategorie() );
		Categorie categorie;
		if ( categorieTmp.isPresent() ) {
			categorie = categorieTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez une categorie valide ", HttpStatus.BAD_REQUEST );
		}
		
		Optional<Conteneur> conteneurTmp = conteneurRepository.findByNom( articleDTO.getConteneur() );
		Conteneur conteneur;
		if ( conteneurTmp.isPresent() ) {
			conteneur = conteneurTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez un conteneur valide ", HttpStatus.BAD_REQUEST );
		}
		
		article.setNom( articleDTO.getNom() );
		article.setLibelle( articleDTO.getLibelle() );
	
		List<OptionArticle> listOptions =  new ArrayList<>( article.getOptions() );
		for ( int i = 0; i < listOptions.size(); i++ ) {
			OptionArticle optionArticle = optionArticleRepository.findById( listOptions.get(i).getId() ).get();
			optionArticleRepository.deleteById( optionArticle.getId() );
		}
		article.setOptions(null);
		article.setCategorie(categorie); 
		
		article.setConteneur(conteneur);
		article.setPrixAchat( articleDTO.getPrixAchat() );
		article.setPrix( articleDTO.getPrix() );
		
		article.setPhotos( null );
		System.out.println( " DDD " );
        articleRepository.save(article);
        System.out.println( " EEE " );
        
        String options = articleDTO.getOptions();        
        StringSearcher searcher = new StringSearcher();
        
        List<Integer> keyIndexes = searcher.indexesOf( "{", options);
        List<Integer> keyEndIndexes = searcher.indexesOf( ":", options);
        List<Integer> valueIndexes = searcher.indexesOf( ":", options);
        List<Integer> valueEndIndexes = searcher.indexesOf( "}", options);
        
        for (int i=0; i<keyIndexes.size(); i++) {
        	
        	String option = options.substring( keyIndexes.get(i)+1, keyEndIndexes.get(i));      	
        	String valeur = options.substring( valueIndexes.get(i)+1, valueEndIndexes.get(i));      	
        	
        	Optional<OptionCategorie> optionCategorieTmp = optionCategorieRepository.findByNom( option );
        	OptionCategorie optionCategorie;
    		if ( optionCategorieTmp.isPresent() ) {
    			optionCategorie = optionCategorieTmp.get();
    		} else {
    			return new ResponseEntity<>( "Renseignez une Option valide", HttpStatus.BAD_REQUEST );
    		}	
        	
        	OptionArticle optionArticle = new OptionArticle( article, optionCategorie, valeur);
        	optionArticleRepository.save(optionArticle);
        }
        
        System.out.println( " Article modified " );
        
		return new ResponseEntity<>( "Article Modifie", HttpStatus.CREATED );
	}
	
	@DeleteMapping()
	public ResponseEntity<String> deleteArticle( @RequestBody ArticleDTO articleDTO ) {
	
		System.out.println( " Received Delete Request " );
		
		Optional<Article> articleTmp = articleRepository.findById( articleDTO.getId() );
		Article article;
		if ( articleTmp.isPresent() ) {
			article = articleTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez un article existant ", HttpStatus.BAD_REQUEST );
		}
		
		articleRepository.deleteById( article.getId() );
		
        System.out.println( " Deletes " );
        
		return new ResponseEntity<>( "Article Supprimee", HttpStatus.OK );
	}
	 
}
