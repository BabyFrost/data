package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.dto.RetourDTO;
import com.docteurfrost.data.model.Retour;
import com.docteurfrost.data.model.Utilisateur;
import com.docteurfrost.data.model.Vente;
import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.repository.ArticleRepository;
import com.docteurfrost.data.repository.RetourRepository;
import com.docteurfrost.data.repository.UtilisateurRepository;
import com.docteurfrost.data.repository.VenteRepository;

@RequestMapping("/retours")
@RestController
public class RetourController {
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	RetourRepository retourRepository;
	
	@Autowired
	UtilisateurRepository utilisateurRepository;
	
	@Autowired
	VenteRepository venteRepository;
	
	@GetMapping()
	@ResponseBody
	public Iterable<RetourDTO> getAllRetours( ) {
		
		List<Retour> retours = new ArrayList<>();
		retourRepository.findAll().forEach(retours::add);
		
		List<RetourDTO> retoursDTO = new ArrayList<>();
		for (int i=0; i<retours.size(); i++) {
			retoursDTO.add( new RetourDTO( retours.get(i) ) );
		}
		
		return retoursDTO;
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<String> vendre(@RequestBody RetourDTO retourDTO) {
		
		Utilisateur vendeur;
		Optional<Utilisateur> vendeurTmp = utilisateurRepository.findById( retourDTO.getVendeur().getId() );
		if ( vendeurTmp.isPresent() ) {
			vendeur = vendeurTmp.get();
		} else {
			return new ResponseEntity<>( " Ce Vendeur n'existe pas ", HttpStatus.BAD_REQUEST );
		}
		
		Vente vente;
		Optional<Vente> venteTmp = venteRepository.findById( retourDTO.getVente().getId() );
		if ( venteTmp.isPresent() ) {
			vente = venteTmp.get();
			
			if ( vente.getRetour() != null ) {
				return new ResponseEntity<>( " Cette vente a deja ete remboursee ", HttpStatus.BAD_REQUEST );
			}
			
		} else {
			return new ResponseEntity<>( " Cette vente n'existe pas ", HttpStatus.BAD_REQUEST );
		}
		
		Article article = vente.getArticle();
		
		Retour retour = new Retour ( retourDTO.getLibelle(), vendeur, vente );
		article.retourner();
		articleRepository.save( article );
		retourRepository.save(retour);
		
		return new ResponseEntity<>( "Success", HttpStatus.OK );
	}

}
