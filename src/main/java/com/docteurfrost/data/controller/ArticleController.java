package com.docteurfrost.data.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.docteurfrost.data.categorie.Categorie;
import com.docteurfrost.data.categorie.OptionArticle;
import com.docteurfrost.data.categorie.OptionCategorie;
import com.docteurfrost.data.categorie.ValeurOption;
import com.docteurfrost.data.conteneur.Conteneur;
import com.docteurfrost.data.dto.ArticleDTO;
import com.docteurfrost.data.file.FileStorageService;
import com.docteurfrost.data.model.Marque;
import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.model.article.DansConteneur;
import com.docteurfrost.data.model.article.EnMagasin;
import com.docteurfrost.data.repository.ArticleRepository;
import com.docteurfrost.data.repository.CategorieRepository;
import com.docteurfrost.data.repository.ConteneurRepository;
import com.docteurfrost.data.repository.MarqueRepository;
import com.docteurfrost.data.repository.OptionArticleRepository;
import com.docteurfrost.data.repository.OptionCategorieRepository;
import com.docteurfrost.data.tools.DateStringConverter;
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
	public Iterable<ArticleDTO> getAllArticlesDTO( @RequestParam(required = false) String conteneur, @RequestParam(required = false) String categorie ) {
		
		if ( categorie == null && conteneur == null ) {
			
			List<Article> articles = new ArrayList<>();
			articleRepository.findAll().forEach(articles::add);
			
			List<ArticleDTO> articlesDTO = new ArrayList<>();
			for (int i=0; i<articles.size(); i++) {
				articlesDTO.add( new ArticleDTO( articles.get(i) ) );
			}
			
			return articlesDTO;
			
		} else if ( categorie == null ) {
			
			if ( !( conteneur.matches("[0-9]+") ) ) {
				return null;
			}
			
			Optional<Conteneur> conteneurTmp = conteneurRepository.findById( Integer.valueOf(conteneur) );
			if ( conteneurTmp.isPresent() ) {
				
				List<Article> articles = new ArrayList<>();
				articleRepository.findAllByConteneur( conteneurTmp.get() ).forEach( articles::add );
				
				List<ArticleDTO> articlesDTO = new ArrayList<>();
				for (int i=0; i<articles.size(); i++) {
					articlesDTO.add( new ArticleDTO( articles.get(i) ) );
				}
				
				return articlesDTO;
			}
				
			
			
		} else if ( conteneur == null ) {
			
			Optional<Categorie> categorieTmp = categorieRepository.findById( categorie );
			if ( categorieTmp.isPresent() ) {
				
				List<Article> articles = new ArrayList<>();
				articleRepository.findAllByCategorie( categorieTmp.get() ).forEach( articles::add );
				
				List<ArticleDTO> articlesDTO = new ArrayList<>();
				for (int i=0; i<articles.size(); i++) {
					articlesDTO.add( new ArticleDTO( articles.get(i) ) );
				}
				
				return articlesDTO;
			} 
			
		} else {
			
			Optional<Categorie> categorieTmp = categorieRepository.findById( categorie );
			if ( categorieTmp.isPresent() ) {
				
				Optional<Conteneur> conteneurTmp = conteneurRepository.findById( Integer.valueOf(conteneur) );
				if ( conteneurTmp.isPresent() ) {
					
					if ( !( conteneur.matches("[0-9]+") ) ) {
						return null;
					}
					
					List<Article> articles = new ArrayList<>();
					articleRepository.findAllByCategorieAndConteneur( categorieTmp.get(), conteneurTmp.get() ).forEach( articles::add );
					
					List<ArticleDTO> articlesDTO = new ArrayList<>();
					for (int i=0; i<articles.size(); i++) {
						articlesDTO.add( new ArticleDTO( articles.get(i) ) );
					}
					
					return articlesDTO;
				}
				
			}
			
		}
		 
		
		return null;
		
	}
	
	@GetMapping("/{idArtcile}")
	@ResponseBody 
	public ArticleDTO getArticleSpecifique( @PathVariable("idArtcile") String idArticle ) {
		Optional<Article> articleTmp = articleRepository.findById(idArticle);
		if ( articleTmp.isPresent() ) {
			return new ArticleDTO( articleTmp.get() );
		} else {
			return null;
		} 
	}
	
	@PostMapping()
//	public ResponseEntity<String> saveArticle( @RequestBody ArticleDTO articleDTO ) {
	public ResponseEntity<String> saveArticle( @Valid @ModelAttribute ArticleDTO articleDTO ) throws ParseException {
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
		if ( articleDTO.getCategorie() == null ) {
			return new ResponseEntity<>( " Renseignez une Categorie ", HttpStatus.BAD_REQUEST );
		}
		Optional<Categorie> categorieTmp = categorieRepository.findById( articleDTO.getCategorie() );
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
		if ( articleDTO.getMarque() == null ) {
			return new ResponseEntity<>( " Renseignez une Marque ", HttpStatus.BAD_REQUEST );
		}
		Optional<Marque> marqueTmp = marqueRepository.findById( articleDTO.getMarque() );
		Marque marque;
		if ( marqueTmp.isPresent() ) {
			marque = marqueTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez une marque valide ", HttpStatus.BAD_REQUEST );
		}
		
		System.out.println("BBB");
		if ( articleDTO.getNom() == null  ) {
			return new ResponseEntity<>( " Renseignez le nom de l'article ", HttpStatus.BAD_REQUEST );
		}
		
		System.out.println("CCC");
		if ( articleDTO.getPrixAchat() == 0  ) {
			return new ResponseEntity<>( " Renseignez prix Achat", HttpStatus.BAD_REQUEST );
		}
		
		System.out.println("DDD");
		if ( articleDTO.getPrix() == 0  ) {
			return new ResponseEntity<>( " Renseignez prix Achat", HttpStatus.BAD_REQUEST );
		}
		
		if ( articleRepository.findById( articleDTO.getNom()+"_"+articleDTO.getConteneur() ).isPresent() ) {
			return new ResponseEntity<>( "Cet article existe deja", HttpStatus.CONFLICT );
		}
		
		System.out.println( "Before Insert : "+DateStringConverter.stringToDate( articleDTO.getDateAchat() ) );
		if ( articleDTO.getEtat() == null  ) {
			return new ResponseEntity<>( " Renseignez etat de l'article ", HttpStatus.BAD_REQUEST );
		}
		Article article = new Article(articleDTO.getNom(), articleDTO.getObservation(), null, categorie, conteneur, marque, articleDTO.getPrixAchat(), 0, 0, articleDTO.getPrix(), fileDownloadUri, DateStringConverter.stringToDate( articleDTO.getDateAchat() ), articleDTO.getEtat() );   
		
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
        List<OptionArticle> listOptions = new ArrayList<>();
        for (int i=0; i<keyIndexes.size(); i++) {
        	
        	String option = options.substring( keyIndexes.get(i)+1, keyEndIndexes.get(i));      	
        	String valeur = options.substring( valueIndexes.get(i)+1, valueEndIndexes.get(i)).toUpperCase();      	
        	
        	System.out.println( option+"_"+article.getCategorie().getNom().toUpperCase() );
        	Optional<OptionCategorie> optionCategorieTmp = optionCategorieRepository.findById( option+"_"+article.getCategorie().getNom().toUpperCase() );
        	OptionCategorie optionCategorie;
    		if ( optionCategorieTmp.isPresent() ) {
    			optionCategorie = optionCategorieTmp.get();
    			
    			List<ValeurOption> objetValeursPossible = new ArrayList<>();
    			optionCategorie.getValeurs().forEach(objetValeursPossible::add);
    			
    			List<String> valeursPossible = new ArrayList<>();
    			for (int y=0; y<objetValeursPossible.size(); y++) {
    				valeursPossible.add( objetValeursPossible.get(y).getValeur() );
    			}
    			if ( optionCategorie.getIsFree() == false ) {
    				System.out.println("Size = "+valeursPossible.size() );
    				System.out.println("Val = "+valeursPossible.get(0) );
    				System.out.println("Val = "+valeursPossible.get(1) );
    				System.out.println("Celui ci est conditionee");
    				if ( !valeursPossible.contains(valeur) ) {
    					System.out.println(valeur);
    					System.out.println( !valeursPossible.contains(valeur) );
        				return new ResponseEntity<>( "Valeur d'option invalide", HttpStatus.BAD_REQUEST );
        			}
    			}
    			
    		} else {
    			return new ResponseEntity<>( "Renseignez une Option valide", HttpStatus.BAD_REQUEST );
    		}	
        	
        	OptionArticle optionArticle = new OptionArticle( article, optionCategorie, valeur);
        	listOptions.add(optionArticle);
        	
        }
        
        articleRepository.save( article );
        optionArticleRepository.saveAll(listOptions);
        System.out.println( " Article saved " );
        
		return new ResponseEntity<>( "Article cree", HttpStatus.CREATED );
	}
	
	@PutMapping()
//	public ResponseEntity<String> updateArticle( @RequestBody ArticleDTO articleDTO ) throws ParseException {
	public ResponseEntity<String> updateArticle( @ModelAttribute ArticleDTO articleDTO ) throws ParseException {
		
		System.out.println( " Received Update Request " );
		
		Optional<Article> articleTmp = articleRepository.findById( articleDTO.getId() );
		Article article;
		if ( articleTmp.isPresent() ) {
			article = articleTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez un article existant ", HttpStatus.BAD_REQUEST );
		}
		
		System.out.println( " AAA " );
		Optional<Categorie> categorieTmp = categorieRepository.findById( articleDTO.getCategorie() );
		Categorie categorie;
		if ( categorieTmp.isPresent() ) {
			categorie = categorieTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez une categorie valide ", HttpStatus.BAD_REQUEST );
		}
		
		System.out.println( " BBB " );
		Optional<Conteneur> conteneurTmp = conteneurRepository.findById( articleDTO.getConteneur() );
		Conteneur conteneur;
		if ( conteneurTmp.isPresent() ) {
			conteneur = conteneurTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez un conteneur valide ", HttpStatus.BAD_REQUEST );
		}
		
		System.out.println( " CCC " );
		Optional<Marque> marqueTmp = marqueRepository.findById( articleDTO.getMarque() );
		Marque marque;
		if ( marqueTmp.isPresent() ) {
			marque = marqueTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez une marque valide ", HttpStatus.BAD_REQUEST );
		}
	
		System.out.println( " DDD " );
		List<OptionArticle> listOptions =  new ArrayList<>( article.getOptions() );
		for ( int i = 0; i < listOptions.size(); i++ ) {
			OptionArticle optionArticle = optionArticleRepository.findById( listOptions.get(i).getId() ).get();
			optionArticleRepository.deleteById( optionArticle.getId() );
		}
		article.setOptions(null);
		
		System.out.println("EEE");
		if ( articleDTO.getPrixAchat() == 0  ) {
			return new ResponseEntity<>( " Renseignez prix Achat", HttpStatus.BAD_REQUEST );
		}
		
		System.out.println("FFF");
		if ( articleDTO.getPrix() == 0  ) {
			return new ResponseEntity<>( " Renseignez prix Achat", HttpStatus.BAD_REQUEST );
		}
		
		
		article.setObservation( articleDTO.getObservation() );
		article.setNumeroDeSerie( articleDTO.getNumeroDeSerie() );
		article.setCategorie(categorie);
		article.setConteneur(conteneur);
		article.setMarque(marque);
		article.setPrixAchat( articleDTO.getPrixAchat() );
		article.setPrixLiquidation( articleDTO.getPrixLiquidation() );
		article.setPrixEstimatif( articleDTO.getPrixEstimatif() );
		article.setPrix( articleDTO.getPrix() );
		article.setDateAchat( DateStringConverter.stringToDate( articleDTO.getDateAchat() ) );
		article.setEtat( articleDTO.getEtat() );
        
        String options = articleDTO.getOptions();        
        StringSearcher searcher = new StringSearcher();
        
        List<Integer> keyIndexes = searcher.indexesOf( "{", options);
        List<Integer> keyEndIndexes = searcher.indexesOf( ":", options);
        List<Integer> valueIndexes = searcher.indexesOf( ":", options);
        List<Integer> valueEndIndexes = searcher.indexesOf( "}", options);
        
        System.out.println( " EEE " );
        listOptions = new ArrayList<>();
        for (int i=0; i<keyIndexes.size(); i++) {
        	
        	String option = options.substring( keyIndexes.get(i)+1, keyEndIndexes.get(i));      	
        	String valeur = options.substring( valueIndexes.get(i)+1, valueEndIndexes.get(i)).toUpperCase();      	
        	
        	Optional<OptionCategorie> optionCategorieTmp = optionCategorieRepository.findById( option+"_"+article.getCategorie().getNom() );
        	OptionCategorie optionCategorie;
    		if ( optionCategorieTmp.isPresent() ) {
    			optionCategorie = optionCategorieTmp.get();
    		} else {
    			return new ResponseEntity<>( "Renseignez une Option valide" , HttpStatus.BAD_REQUEST );
    		}	
        	
        	OptionArticle optionArticle = new OptionArticle( article, optionCategorie, valeur);
        	listOptions.add(optionArticle);
        	
        }
        
        System.out.println( " FFF " );
        if ( articleDTO.getFile() != null ) {
			String fileName = fileStorageService.storeFile( articleDTO.getFile(), articleDTO.getNom());

	        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(articleDTO.getNom()+"/"+fileName)
	                .toUriString();
	        
	        article.setPhoto( fileDownloadUri );
		}
        
        articleRepository.save(article);
        optionArticleRepository.saveAll(listOptions);
        System.out.println( " Article modified " );
        
		return new ResponseEntity<>( "Article Modifie" , HttpStatus.CREATED );
	}
	
	@DeleteMapping("/{idArticle}")
	public ResponseEntity<String> deleteArticle( @PathVariable("idArticle") String idArticle ) {
	
		System.out.println( " Received Delete Request" );
		
		Optional<Article> articleTmp = articleRepository.findById( idArticle );
		Article article;
		if ( articleTmp.isPresent() ) {
			article = articleTmp.get();
		} else {
			return new ResponseEntity<>( " Renseignez un article existant ", HttpStatus.BAD_REQUEST );
		}
		
		articleRepository.deleteById( article.getNom()+"_"+article.getConteneur().getId() );
		
        System.out.println( " Deleted " );
        
		return new ResponseEntity<>( "Article Supprimee", HttpStatus.OK );
	}
	
	@PatchMapping("/mise_en_vente")
	@ResponseBody
	public ResponseEntity<String> miseEnVenteArticles( @RequestBody List<ArticleDTO> articlesDTO, @RequestParam String date ) throws ParseException {
//	public ResponseEntity<String> updateArticle( @ModelAttribute List<ArticleDTO> articlesDTO, @RequestParam String date ) throws ParseException {
		
		for (int i = 0; i<articlesDTO.size(); i++) {
			
			Article article;
			Optional<Article> articleTmp = articleRepository.findById( articlesDTO.get(i).getId() );  
			if ( articleTmp.isPresent() ) {
				
				article = articleTmp.get();
				if ( article.getDateMiseEnVente() == null ) {
					
					article.setDateMiseEnVente( DateStringConverter.stringToDate( date ) );
					
					if ( article.getState() instanceof DansConteneur ) {
						
						return new ResponseEntity<>( "Article encore dans le conteneur "+article.getId(), HttpStatus.BAD_REQUEST );
						
					} else if ( !( article.getState() instanceof EnMagasin ) ) {
						
						return new ResponseEntity<>( "l'article n'est plus en Magasin", HttpStatus.BAD_REQUEST );
						
					}
					
					if ( article.getDateMiseEnVente().before( article.getDateSaisie() ) ) {
						return new ResponseEntity<>( "Date < Date Depart", HttpStatus.BAD_REQUEST );
					}
					
					article.getState().deballer();
					articleRepository.save( article );
//					return new ResponseEntity<>( "Status Modifie", HttpStatus.OK );
				} else {
					return new ResponseEntity<>( "Article deja Mis En Vente", HttpStatus.ALREADY_REPORTED );
				}
				
			} else {
				return new ResponseEntity<>( "Article inexistant", HttpStatus.CONFLICT );
			}
			
		}
		
		return new ResponseEntity<>( "Tous les Status Modifie", HttpStatus.OK );
	}
	
	@PostMapping("/validate/validateArticle")
//	ResponseEntity<String> validateBody(@Valid @RequestBody Car car) {
	ResponseEntity<String> validateBody(@Valid @ModelAttribute ArticleDTO articleDTO) {
		return ResponseEntity.ok("valid");
	}
	 
}
