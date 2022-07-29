package com.docteurfrost.data.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.dto.PanierDTO;
import com.docteurfrost.data.dto.VenteDTO;
import com.docteurfrost.data.dto.interne.ArticleInterneDTO;
import com.docteurfrost.data.exception.BadRequestException;
import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Client;
import com.docteurfrost.data.model.Panier;
import com.docteurfrost.data.model.Utilisateur;
import com.docteurfrost.data.model.Vente;
import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.model.article.EnVente;
import com.docteurfrost.data.model.reservation.Reservation;
import com.docteurfrost.data.service.ArticleService;
import com.docteurfrost.data.service.ClientService;
import com.docteurfrost.data.service.PanierService;
import com.docteurfrost.data.service.UtilisateurService;
import com.docteurfrost.data.service.VenteService;
import com.docteurfrost.data.tools.DateStringConverter;

@RequestMapping("/ventes")
@RestController
public class VenteController {
	
	@Autowired
	private VenteService venteService;
	
	@Autowired
	private PanierService panierService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@GetMapping("/{idVente}")
	@ResponseBody
	public VenteDTO getVenteById( @PathVariable("idVente") int idVente ) throws ResourceNotFoundException {
		return new VenteDTO( venteService.getVenteById( idVente ) );
	}
	
	@GetMapping()
	@ResponseBody
	public List<VenteDTO> getAllVentes( @RequestParam(required = false, name = "dateDebut") String dateDebut, @RequestParam(required = false, name = "dateFin") String dateFin ) throws ResourceNotFoundException, ParseException {
		
		List<Vente> ventes = new ArrayList<>();
		if ( dateDebut == null && dateFin == null) {
			ventes = venteService.getAllVente();
		} else {
			ventes = venteService.getAllVenteByIntervalDate( DateStringConverter.stringToDate(dateDebut), DateStringConverter.stringToDate(dateFin) );
		}
		
		List<VenteDTO> ventesDTO = new ArrayList<>();
		for (int i=0; i<ventes.size(); i++) {
			ventesDTO.add( new VenteDTO( ventes.get(i) ) );
		}
		
		return ventesDTO;
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<PanierDTO> vendre( @Valid @RequestBody PanierDTO panierDTO) throws ResourceNotFoundException, BadRequestException, ResourceConflictException {
		
		Panier panier = new Panier( new Date() );
		
		int nombreArticles = 0;
		int montantTotal = 0;
		List<Article> articles = new ArrayList<>();
		List<Vente> ventes = new ArrayList<>();
		
		List<ArticleInterneDTO> articlesDTO = panierDTO.getArticles();
		for ( int i = 0; i < articlesDTO.size(); i++) {
			ArticleInterneDTO articleDTO = articlesDTO.get(i);
			
			Client client = clientService.getClientById( panierDTO.getClient().getTelephone() );
			Utilisateur vendeur = utilisateurService.getUtilisateurById( panierDTO.getVendeur().getId() );
			
			Article article = articleService.getArticleById( articleDTO.getId() );
			if ( !(article.getState() instanceof EnVente) ) {
				throw new BadRequestException("Cette article n'est pas a vendre");
			}

			Vente vente = new Vente ( "Libelle", client, article, vendeur, panier );
			article.vendre();
			
			ventes.add(vente);
			articles.add(article);
			
			nombreArticles++;
			montantTotal += article.getPrix();
			
		}
		
		panier.setNombreArticles(nombreArticles);
		panier.setMontantTotal(montantTotal);
		panier.setVentes( ventes );
		panier = panierService.createPanier(panier);
		
		articleService.updateAllArticle(articles);
//		venteService.createAllVente(ventes);
//		panier = panierService.getPanierById( panier.getId() );	
//		PanierDTO responsePanierDTO = new PanierDTO( panier );
		return new ResponseEntity<>( new PanierDTO( panier ), HttpStatus.OK );
	}
	
	public Panier completerReservation ( Reservation reservation, Utilisateur vendeur) {
		
		Panier panier = new Panier( new Date() );
		
		Article article = reservation.getArticle();
		Client client = reservation.getClient();
		List<Vente> ventes = new ArrayList<>();
		
		Vente vente = new Vente ( "Cette Reservation a ete completee", client, article, vendeur, panier );
		
		article.vendre();	
		ventes.add(vente);
		
		panier.setNombreArticles(1);
		panier.setMontantTotal( article.getPrix() );
		panier.setVentes( ventes );
		panier = panierService.createPanier(panier);
		article = articleService.updateArticle(article);
		
		return panier;
	}

}
