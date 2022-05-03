package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.docteurfrost.data.categorie.Categorie;
import com.docteurfrost.data.categorie.OptionArticle;
import com.docteurfrost.data.categorie.OptionCategorie;
import com.docteurfrost.data.conteneur.Conteneur;
import com.docteurfrost.data.dto.ArticleDTO;
import com.docteurfrost.data.file.FileStorageService;
import com.docteurfrost.data.model.Marque;
import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.repository.ArticleRepository;
import com.docteurfrost.data.repository.CategorieRepository;
import com.docteurfrost.data.repository.ConteneurRepository;
import com.docteurfrost.data.repository.MarqueRepository;
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
	
	@Autowired
	private MarqueRepository marqueRepository;
	
	@Autowired
    private FileStorageService fileStorageService;
	
	@GetMapping()
	@ResponseBody
	public Iterable<ArticleDTO> getAllArticlesDTO( ) {
		
		List<Article> articles = new ArrayList<>();
		articleRepository.findAll().forEach(articles::add);
		
		List<ArticleDTO> articlesDTO = new ArrayList<>();
		for (int i=0; i<articles.size(); i++) {
			articlesDTO.add( new ArticleDTO( articles.get(i) ) );
		}
		
		return articlesDTO;
	}
	
	@GetMapping("/{nomArtcile}")
	@ResponseBody 
	public ArticleDTO getArticleSpecifique( @PathVariable("nomArtcile") String nomArtcile ) {
		Optional<Article> articleTmp = articleRepository.findById(nomArtcile);
		  
		if ( articleTmp.isPresent() ) {
			return new ArticleDTO( articleTmp.get() );
		} else {
			return null;
		}
	  
	}
	
	
	@GetMapping("/categorie/{categorie}")
	@ResponseBody 
	public Iterable<Article> getArticlesByCategorie( @PathVariable("categorie") String nomCategorie ) {
		Optional<Categorie> categorie = categorieRepository.findById(nomCategorie);
		  
		if ( categorie.isPresent() ) {
			return articleRepository.findAllByCategorie( categorie.get() );
		} else {
			return null;
		}
	  
	}
	
	@PostMapping()
	public ResponseEntity<String> saveArticle( @RequestBody ArticleDTO articleDTO ) {
//	public ResponseEntity<String> saveArticle( @ModelAttribute ArticleDTO articleDTO ) {
		System.out.println("Ca entre");
		
		String fileDownloadUri = null;
		if ( articleDTO.getFile() != null ) {
			System.out.println(" File n'est pas null ");
			String fileName = fileStorageService.storeFile( articleDTO.getFile(), articleDTO.getNom());

	        fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(articleDTO.getNom()+"/"+fileName)
	                .toUriString();
		}
		
		System.out.println("Ca sort");
		
		System.out.println( " Received Save Article Request" );
		
		System.out.println("xxx");
		System.out.println(articleDTO.getCategorie());
		Optional<Categorie> categorieTmp = categorieRepository.findById( articleDTO.getCategorie() );
		System.out.println("xyxy");
		Categorie categorie;
		if ( categorieTmp.isPresent() ) {
			System.out.println("yyy");
			categorie = categorieTmp.get();
		} else {
			System.out.println(articleDTO.getCategorie());
			return new ResponseEntity<>( " Renseignez une categorie valide ", HttpStatus.BAD_REQUEST );
		}
		
		System.out.println("AAA");
		Optional<Conteneur> conteneurTmp = conteneurRepository.findById( articleDTO.getConteneur() );
		Conteneur conteneur;
		if ( conteneurTmp.isPresent() ) {
			conteneur = conteneurTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez un conteneur valide ", HttpStatus.BAD_REQUEST );
		}
		
		System.out.println( articleDTO.getMarque() );
		
		Optional<Marque> marqueTmp = marqueRepository.findById( articleDTO.getMarque() );
		Marque marque;
		if ( marqueTmp.isPresent() ) {
			marque = marqueTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez une marque valide ", HttpStatus.BAD_REQUEST );
		}
		
		System.out.println("BBB");
		if ( articleRepository.findById( articleDTO.getNom() ).isPresent() ) {
			return new ResponseEntity<>( "Cet article existe deja", HttpStatus.CONFLICT );
		}
		
		System.out.println("VIP OR NOT");
		if ( articleDTO.getVip() == null ) {
			return new ResponseEntity<>( "Renseignez vip", HttpStatus.BAD_REQUEST );
		}
		
		Article article = new Article(articleDTO.getNom(), articleDTO.getObservation(), categorie, conteneur, marque, articleDTO.getPrixAchat(), 0, 0, articleDTO.getPrix(), fileDownloadUri, articleDTO.getVip(), null );   
        articleRepository.save( article );
		
        String options = articleDTO.getOptions();
        if ( options == null ) {
        	options = "";
        }
        
        StringSearcher searcher = new StringSearcher();
        
        List<Integer> keyIndexes = searcher.indexesOf( "{", options);
        List<Integer> keyEndIndexes = searcher.indexesOf( ":", options);
        List<Integer> valueIndexes = searcher.indexesOf( ":", options);
        List<Integer> valueEndIndexes = searcher.indexesOf( "}", options);
        
        System.out.println("CCC");
        for (int i=0; i<keyIndexes.size(); i++) {
        	
        	String option = options.substring( keyIndexes.get(i)+1, keyEndIndexes.get(i));      	
        	String valeur = options.substring( valueIndexes.get(i)+1, valueEndIndexes.get(i));      	
        	
        	//CONSTRUIRE L'ID ET ENSUITE FAIRE FIND BY ID
        	System.out.println( article.getCategorie().getNom()+"_"+option );
        	Optional<OptionCategorie> optionCategorieTmp = optionCategorieRepository.findById( option+"_"+article.getCategorie().getNom() );
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
		
		Optional<Article> articleTmp = articleRepository.findById( articleDTO.getNom() );
		Article article;
		if ( articleTmp.isPresent() ) {
			article = articleTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez un article existant ", HttpStatus.BAD_REQUEST );
		}
		
		Optional<Categorie> categorieTmp = categorieRepository.findById( articleDTO.getCategorie() );
		Categorie categorie;
		if ( categorieTmp.isPresent() ) {
			categorie = categorieTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez une categorie valide ", HttpStatus.BAD_REQUEST );
		}
		
		Optional<Conteneur> conteneurTmp = conteneurRepository.findById( articleDTO.getConteneur() );
		Conteneur conteneur;
		if ( conteneurTmp.isPresent() ) {
			conteneur = conteneurTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez un conteneur valide ", HttpStatus.BAD_REQUEST );
		}
		
		Optional<Marque> marqueTmp = marqueRepository.findById( articleDTO.getMarque() );
		Marque marque;
		if ( marqueTmp.isPresent() ) {
			marque = marqueTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez une marque valide ", HttpStatus.BAD_REQUEST );
		}
		
//		article.setNom( articleDTO.getNom() );
		
	
		List<OptionArticle> listOptions =  new ArrayList<>( article.getOptions() );
		for ( int i = 0; i < listOptions.size(); i++ ) {
			OptionArticle optionArticle = optionArticleRepository.findById( listOptions.get(i).getId() ).get();
			optionArticleRepository.deleteById( optionArticle.getId() );
		}
		article.setOptions(null);
		
		if ( articleDTO.getFile() != null ) {
			String fileName = fileStorageService.storeFile( articleDTO.getFile(), articleDTO.getNom());

	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(articleDTO.getNom()+"/"+fileName)
	                .toUriString();
	        
	        article.setPhoto( fileDownloadUri );
		}
		
		article.setObservation( articleDTO.getObservation() );
		article.setCategorie(categorie);
		article.setConteneur(conteneur);
		article.setMarque(marque);
		article.setPrixAchat( articleDTO.getPrixAchat() );
		article.setPrixLiquidation( articleDTO.getPrixLiquidation() );
		article.setPrixEstimatif( articleDTO.getPrixEstimatif() );
		article.setPrix( articleDTO.getPrix() );
		article.setVip( articleDTO.getVip() );
		article.setDateAchat( articleDTO.getDateAchat() );
		
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
        	
        	//CONSTRUIRE L'ID ET ENSUITE FAIRE FIND BY ID
        	Optional<OptionCategorie> optionCategorieTmp = optionCategorieRepository.findById( option+"_"+article.getCategorie().getNom() );
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
	
	@DeleteMapping("/{nomArticle}")
	public ResponseEntity<String> deleteArticle( @PathVariable("nomArticle") String nomArticle ) {
	
		System.out.println( " Received Delete Request" );
		
		Optional<Article> articleTmp = articleRepository.findById( nomArticle );
		Article article;
		if ( articleTmp.isPresent() ) {
			article = articleTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez un article existant ", HttpStatus.BAD_REQUEST );
		}
		
		articleRepository.deleteById( article.getNom() );
		
        System.out.println( " Deleted " );
        
		return new ResponseEntity<>( "Article Supprimee", HttpStatus.OK );
	}
	 
}
