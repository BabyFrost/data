package com.docteurfrost.data.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import com.docteurfrost.data.exception.BadRequestException;
import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.file.FileStorageService;
import com.docteurfrost.data.model.Marque;
import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.model.article.ArticleState;
import com.docteurfrost.data.model.article.DansConteneur;
import com.docteurfrost.data.model.article.Disparu;
import com.docteurfrost.data.model.article.EnMagasin;
import com.docteurfrost.data.model.article.EnVente;
import com.docteurfrost.data.model.article.Reserve;
import com.docteurfrost.data.model.article.Vendu;
import com.docteurfrost.data.service.ArticleService;
import com.docteurfrost.data.service.CategorieService;
import com.docteurfrost.data.service.ConteneurService;
import com.docteurfrost.data.service.MarqueService;
import com.docteurfrost.data.service.OptionArticleService;
import com.docteurfrost.data.service.OptionCategorieService;
import com.docteurfrost.data.tools.DateStringConverter;
import com.docteurfrost.data.tools.StringSearcher;

@RequestMapping("/articles")
@RestController
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategorieService categorieService;
	
	@Autowired
	private ConteneurService conteneurService;
	
	@Autowired
	private OptionCategorieService optionCategorieService;
	
	@Autowired
	private OptionArticleService optionArticleService;
	
	@Autowired
	private MarqueService marqueService;
	
	@Autowired
    private FileStorageService fileStorageService;
	
	@GetMapping()
	@ResponseBody
	public List<ArticleDTO> getAllArticles( @RequestParam(required = false, name = "conteneur") String conteneurStr, @RequestParam(required = false, name = "categorie") String idCategorie, @RequestParam(required = false) String status ) throws ResourceNotFoundException, BadRequestException {
		
		int idConteneur = 0;
		if ( conteneurStr != null ) {
			if ( StringUtils.isNumeric(conteneurStr) ) {
				idConteneur = Integer.valueOf(conteneurStr);
			} else {
				throw new BadRequestException("Renseignez un Conteneur valide");
			}
		}
		
		
		List<Article> articles = new ArrayList<>();
		
		if ( idCategorie == null && idConteneur == 0 && status == null ) {
			
			articles = articleService.getAllArticle();
			
		} else if ( idCategorie == null && status == null ) {
			
			Conteneur conteneur = conteneurService.getConteneurById(idConteneur);
			articles = articleService.getAllArticleByConteneur(conteneur);
			
		} else if ( idConteneur == 0 && status == null ) {
			
			Categorie categorie = categorieService.getCategorieById(idCategorie);
			articles = articleService.getAllArticleByCategorie( categorie );
			
		} else if ( idCategorie == null && idConteneur == 0 ) {
			
			ArticleState state = null;
			switch( status.toLowerCase() ) {
				case "disparu":
					state = new Disparu(null);
					break;
				case "enmagasin":
					state = new EnMagasin(null);
					break;
				case "envente":
					state = new EnVente(null);
					break;
				case "reserve":
					state = new Reserve(null);
					break;
				case "vendu":
					state = new Vendu(null);
					break;
				case "dansconteneur":
					state = new DansConteneur(null);
					break;
			}
			
			articles = articleService.getAllArticleByState(state);
			
		} else {
			Categorie categorie = categorieService.getCategorieById(idCategorie);
			Conteneur conteneur = conteneurService.getConteneurById(idConteneur);	
			articles = articleService.getAllArticleByCategorieAndConteneur(categorie, conteneur);
		}
		
		List<ArticleDTO> articlesDTO = new ArrayList<>();
		for (int i=0; i<articles.size(); i++) {
			articlesDTO.add( new ArticleDTO( articles.get(i) ) );
		}	
		return articlesDTO;
		
	}
	
	@GetMapping("/{idArtcile}")
	@ResponseBody 
	public ArticleDTO getArticleSpecifique( @PathVariable("idArtcile") String idArticle ) throws ResourceNotFoundException {
		return new ArticleDTO( articleService.getArticleById(idArticle) );
	}
	
	@PostMapping()
//	public ResponseEntity<String> saveArticle( @RequestBody ArticleDTO articleDTO ) {
	public ResponseEntity<String> saveArticle( @Valid @ModelAttribute ArticleDTO articleDTO ) throws ParseException, ResourceConflictException, BadRequestException, ResourceNotFoundException {
		
		String fileDownloadUri = null;
		if ( articleDTO.getFile() != null ) {
			System.out.println(" File n'est pas null ");
			String fileName = fileStorageService.storeFile( articleDTO.getFile(), articleDTO.getNom());

	        fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
	                .path("/downloadFile/")
	                .path(articleDTO.getNom()+"/"+fileName)
	                .toUriString();
		}
		
		if ( articleDTO.getCategorie() == null ) {
			throw new BadRequestException("Renseignez une Categorie");
		}
		Categorie categorie = categorieService.getCategorieById( articleDTO.getCategorie() );
		
		Conteneur conteneur = conteneurService.getConteneurById( articleDTO.getConteneur() );
		
		if ( articleDTO.getMarque() == null ) {
			throw new BadRequestException("Renseignez une Marque");
		}
		Marque marque = marqueService.getMarqueById( articleDTO.getMarque() );
		
		System.out.println("BBB");
		if ( articleDTO.getNom() == null  ) {
			throw new BadRequestException("Renseignez le nom de l'article");
		}
		
		System.out.println("CCC");
		if ( articleDTO.getPrixAchat() == 0  ) {
			throw new BadRequestException("Renseignez prix Achat");
		}
		
		System.out.println("DDD");
		if ( articleDTO.getPrix() == 0  ) {
			throw new BadRequestException("Renseignez prix de Vente");
		}
		
		System.out.println( "Before Insert : "+DateStringConverter.stringToDate( articleDTO.getDateAchat() ) );
		if ( articleDTO.getEtat() == null  ) {
			throw new BadRequestException("Renseignez etat de l'article");
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
        	OptionCategorie optionCategorie = optionCategorieService.getOptionCategorieById( option+"_"+article.getCategorie().getNom().toUpperCase() );
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
        	
        	OptionArticle optionArticle = new OptionArticle( article, optionCategorie, valeur);
        	listOptions.add(optionArticle);
        	
        }
        
        articleService.createArticle( article );
        optionArticleService.createAllOptionArticle(listOptions);
        System.out.println( " Article saved " );
        
		return new ResponseEntity<>( "Article cree", HttpStatus.CREATED );
	}
	
	@PutMapping()
//	public ResponseEntity<String> updateArticle( @RequestBody ArticleDTO articleDTO ) throws ParseException {
	public ResponseEntity<String> updateArticle( @ModelAttribute ArticleDTO articleDTO ) throws ParseException, ResourceNotFoundException, ResourceConflictException, BadRequestException {
		
		Article article = articleService.getArticleById( articleDTO.getId() );
		
		System.out.println( " AAA " );
		Categorie categorie = categorieService.getCategorieById( articleDTO.getCategorie() );
		
		System.out.println( " BBB " );
		Conteneur conteneur = conteneurService.getConteneurById( articleDTO.getConteneur() );
		
		System.out.println( " CCC " );
		Marque marque = marqueService.getMarqueById( articleDTO.getMarque() );
	
		System.out.println( " DDD " );
		List<OptionArticle> listOptions =  new ArrayList<>( article.getOptions() );
		for ( int i = 0; i < listOptions.size(); i++ ) {
//			OptionArticle optionArticle = optionArticleService.getOptionArticleById( listOptions.get(i).getId() );
			optionArticleService.deleteOptionArticle( listOptions.get(i) );
		}
		article.setOptions(null);
		
		System.out.println("EEE");
		if ( articleDTO.getPrixAchat() == 0  ) {
			throw new BadRequestException("Renseignez prix Achat");
		}
		
		System.out.println("FFF");
		if ( articleDTO.getPrix() == 0  ) {
			throw new BadRequestException("Renseignez prix Achat");
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
        	
        	OptionCategorie optionCategorie = optionCategorieService.getOptionCategorieById( option+"_"+article.getCategorie().getNom() );
        	
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
        
        articleService.updateArticle(article);
        optionArticleService.createAllOptionArticle(listOptions);
        System.out.println( " Article modified " );
        
		return new ResponseEntity<>( "Article Modifie" , HttpStatus.CREATED );
	}
	
	@DeleteMapping("/{idArticle}")
	public ResponseEntity<String> deleteArticle( @PathVariable("idArticle") String idArticle ) throws ResourceNotFoundException {
		articleService.deleteArticleById( idArticle );
		return new ResponseEntity<>( "Article Supprimee", HttpStatus.OK );
	}
	
	@PatchMapping("/status/mise_en_vente")
	@ResponseBody
	public ResponseEntity<String> miseEnVenteArticles( @RequestBody List<ArticleDTO> articlesDTO, @RequestParam String date ) throws ParseException, ResourceNotFoundException, BadRequestException {
//	public ResponseEntity<String> updateArticle( @ModelAttribute List<ArticleDTO> articlesDTO, @RequestParam String date ) throws ParseException {
		
		for (int i = 0; i<articlesDTO.size(); i++) {
			
			Article article = articleService.getArticleById( articlesDTO.get(i).getId() );
			if ( article.getDateMiseEnVente() == null ) {
				
				article.setDateMiseEnVente( DateStringConverter.stringToDate( date ) );
				
				if ( article.getState() instanceof DansConteneur ) {
					throw new BadRequestException("Article "+article.getId()+" encore dans le conteneur");
				} else if ( !( article.getState() instanceof EnMagasin ) ) {
					throw new BadRequestException("l'article n'est plus en Magasin");
				}
				if ( article.getDateMiseEnVente().before( article.getDateSaisie() ) ) {
					throw new BadRequestException("Date < Date Depart");
				}
				
				article.getState().deballer();
				articleService.updateArticle( article );
			} else {
				throw new BadRequestException("Article deja Mis En Vente");
			}
			
		}
		
		return new ResponseEntity<>( "Tous les Status Modifie", HttpStatus.OK );
	}
	 
}
