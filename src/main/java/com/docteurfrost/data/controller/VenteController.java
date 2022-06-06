package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.dto.ArticleDTO;
import com.docteurfrost.data.dto.PanierDTO;
import com.docteurfrost.data.dto.VenteDTO;
import com.docteurfrost.data.model.Client;
import com.docteurfrost.data.model.Panier;
import com.docteurfrost.data.model.Utilisateur;
import com.docteurfrost.data.model.Vente;
import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.model.article.EnVente;
import com.docteurfrost.data.repository.ArticleRepository;
import com.docteurfrost.data.repository.ClientRepository;
import com.docteurfrost.data.repository.PanierRepository;
import com.docteurfrost.data.repository.UtilisateurRepository;
import com.docteurfrost.data.repository.VenteRepository;

@RequestMapping("/ventes")
@RestController
public class VenteController {
	
	@Autowired
	private VenteRepository venteRepository;
	
	@Autowired
	private PanierRepository panierRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@GetMapping()
	@ResponseBody
	public Iterable<VenteDTO> getAllVentesDTO( ) {
		
		System.out.println( "Get Ventes" );
		
		List<Vente> ventes = new ArrayList<>();
		venteRepository.findAll().forEach(ventes::add);
		
		List<VenteDTO> ventesDTO = new ArrayList<>();
		for (int i=0; i<ventes.size(); i++) {
			ventesDTO.add( new VenteDTO( ventes.get(i) ) );
		}
		
		return ventesDTO;
	}
	
	@GetMapping("/{idArticle}")
	@ResponseBody
	public Iterable<VenteDTO> getAllVentesArticleDTO( @PathVariable("idArtcile") String idArticle ) {
		
		List<Vente> ventes = new ArrayList<>();
		venteRepository.findAll().forEach(ventes::add);
		
		List<VenteDTO> ventesDTO = new ArrayList<>();
		for (int i=0; i<ventes.size(); i++) {
			ventesDTO.add( new VenteDTO( ventes.get(i) ) );
		}
		
		return ventesDTO;
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<String> vendre(@RequestBody PanierDTO panierDTO) {
		
		Panier panier = new Panier( new Date() );
		
		int nombreArticles = 0;
		int montantTotal = 0;
		List<Article> articles = new ArrayList<>();
		List<Vente> ventes = new ArrayList<>();
		
		List<ArticleDTO> articlesDTO = panierDTO.getArticles();
		for ( int i = 0; i < articlesDTO.size(); i++) {
			ArticleDTO articleDTO = articlesDTO.get(i);
			
			Client client;
			Optional<Client> clientTmp = clientRepository.findById( panierDTO.getClient().getTelephone() );
			if ( clientTmp.isPresent() ) {
				client = clientTmp.get();
			} else {
				return new ResponseEntity<>( "Ce client n'existe pas ", HttpStatus.BAD_REQUEST );
			}
			
			System.out.println("sfsdgsdg");
			Utilisateur vendeur;
			Optional<Utilisateur> vendeurTmp = utilisateurRepository.findById( panierDTO.getVendeur().getId() );
			if ( vendeurTmp.isPresent() ) {
				vendeur = vendeurTmp.get();
			} else {
				return new ResponseEntity<>( "Ce Vendeur n'existe pas ", HttpStatus.BAD_REQUEST );
			}
			
			
			System.out.println("AAAAA");
			Article article;
			
			Optional<Article> articleTmp = articleRepository.findById( articleDTO.getId() );
			if ( articleTmp.isPresent() ) {
				article = articleTmp.get();
				
				if ( !(article.getState() instanceof EnVente) ) {
					return new ResponseEntity<>( "Cette article n'est pas a vendre ", HttpStatus.BAD_REQUEST );
				}
				
			} else {
				return new ResponseEntity<>( "Renseignez un article valide ", HttpStatus.BAD_REQUEST );
			}

			System.out.println("BBBB");
			Vente vente = new Vente ( "Libelle", client, article, vendeur, panier );
			article.vendre();
			
			ventes.add(vente);
			articles.add(article);
			
			nombreArticles++;
			montantTotal += article.getPrix();
			
		}
		
		panier.setNombreArticles(nombreArticles);
		panier.setMontantTotal(montantTotal);
		panierRepository.save(panier);
		
		articleRepository.saveAll(articles);
		venteRepository.saveAll(ventes);
		
		return new ResponseEntity<>( "Article Vendu", HttpStatus.OK );
	}

}
