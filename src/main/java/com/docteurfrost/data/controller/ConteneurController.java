package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.dto.ConteneurDTO;
import com.docteurfrost.data.model.Conteneur;
import com.docteurfrost.data.repository.ConteneurRepository;

@RequestMapping("/conteneurs")
@RestController
public class ConteneurController {

	@Autowired
	private ConteneurRepository conteneurRepository;
	
	@GetMapping()
	@ResponseBody
	public Iterable<ConteneurDTO> getAllContainers( ) {
		List<Conteneur> conteneurs = new ArrayList<>();
		conteneurRepository.findAll().forEach(conteneurs::add);
		
		List<ConteneurDTO> conteneursDTO = new ArrayList<>();
		for (int i=0; i<conteneurs.size(); i++) {
			conteneursDTO.add( new ConteneurDTO( conteneurs.get(i) ) );
		}
		
		return conteneursDTO;
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<String> saveCategory(@RequestBody ConteneurDTO conteneurDTO) {
		if ( conteneurRepository.findByNom(conteneurDTO.getNom()).isPresent() ) {
			return new ResponseEntity<>( "Ce Conteneur existe deja", HttpStatus.CONFLICT );
		}
		
		Conteneur conteneur = new Conteneur( conteneurDTO.getNom() );
		conteneurRepository.save(conteneur);
		
		return new ResponseEntity<>( "Conteneur cree", HttpStatus.CREATED );
	}
	
}
