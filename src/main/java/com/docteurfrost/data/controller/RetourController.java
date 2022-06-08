package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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

import com.docteurfrost.data.dto.RetourDTO;
import com.docteurfrost.data.exception.BadRequestException;
import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Retour;
import com.docteurfrost.data.model.Utilisateur;
import com.docteurfrost.data.model.Vente;
import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.service.ArticleService;
import com.docteurfrost.data.service.RetourService;
import com.docteurfrost.data.service.UtilisateurService;
import com.docteurfrost.data.service.VenteService;

@RequestMapping("/retours")
@RestController
public class RetourController {
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	RetourService retourService;
	
	@Autowired
	UtilisateurService utilisateurService;
	
	@Autowired
	VenteService venteService;
	
	@GetMapping("/{idRetour}")
	@ResponseBody
	public RetourDTO getRetourById( @PathVariable("idRetour") int idRetour ) throws ResourceNotFoundException {	
		Retour retour = retourService.getRetourById(idRetour);
		return new RetourDTO ( retour );
	}
	
	@GetMapping()
	@ResponseBody
	public List<RetourDTO> getAllRetours( ) throws ResourceNotFoundException {	
		List<Retour> retours = retourService.getAllRetours();
		List<RetourDTO> retoursDTO = new ArrayList<>();
		for (int i=0; i<retours.size(); i++) {
			retoursDTO.add( new RetourDTO( retours.get(i) ) );
		}
		return retoursDTO;
	}
	
	@PostMapping()
	@ResponseBody
	@Transactional
	public ResponseEntity<RetourDTO> vendre(@RequestBody RetourDTO retourDTO) throws ResourceNotFoundException, BadRequestException, ResourceConflictException {
		
		Utilisateur vendeur = utilisateurService.getUtilisateurById( retourDTO.getVendeur().getId() );	
		Vente vente = venteService.getVenteById( retourDTO.getVente().getId() );
		if ( vente.getRetour() != null ) {
			throw new BadRequestException("Vente deja remboursee");
		}
		Article article = vente.getArticle();
		
		Retour retour = new Retour ( retourDTO.getLibelle(), vendeur, vente );
		article.retourner();
		articleService.saveArticle( article );
		
		RetourDTO responseRetourDTO = new RetourDTO( retourService.createRetour(retour) );
		return new ResponseEntity<>( responseRetourDTO, HttpStatus.OK );
	}

}
