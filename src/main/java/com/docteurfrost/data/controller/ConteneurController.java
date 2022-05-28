package com.docteurfrost.data.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.conteneur.Conteneur;
import com.docteurfrost.data.dto.ConteneurDTO;
import com.docteurfrost.data.service.ConteneurService;

@RequestMapping("/conteneurs")
@RestController
public class ConteneurController {

	@Autowired
	private ConteneurService conteneurService;
	
	@GetMapping()
	@ResponseBody
	public List<ConteneurDTO> getAllConteneursDTO( ) {
		
		List<Conteneur> conteneurs = conteneurService.getAllConteneurs();
		List<ConteneurDTO> conteneursDTO = new ArrayList<>();
		for (int i=0; i<conteneurs.size(); i++) {
			conteneursDTO.add( new ConteneurDTO( conteneurs.get(i) ) );
		}
		
		return conteneursDTO;
		
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<String> saveConteneurDTO(@RequestBody ConteneurDTO conteneurDTO) throws ParseException {
		
		return conteneurService.saveConteneurDTO(conteneurDTO);
		
	}
	
	@PutMapping()
	@ResponseBody
	public ResponseEntity<String> modifyConteneurDTO(@RequestBody ConteneurDTO conteneurDTO) throws ParseException {
		
		return conteneurService.modifyConteneurDTO(conteneurDTO);
		
	}
	
	@PatchMapping("/depart/{idConteneur}")
	@ResponseBody
	public ResponseEntity<String> miseEnRouteConteneur( @PathVariable("idConteneur") int idConteneur, @RequestParam String date ) throws ParseException {
		
		return conteneurService.miseEnRouteConteneur(idConteneur, date);
		
	}
	
	@PatchMapping("/arrive/{idConteneur}")
	@ResponseBody
	public ResponseEntity<String> arriveConteneur( @PathVariable("idConteneur") int idConteneur, @RequestParam String date ) throws ParseException {
		
		return conteneurService.arriveConteneur(idConteneur, date);
		
	}
	
	@PatchMapping("/dechargement/{idConteneur}")
	@ResponseBody
	public ResponseEntity<String> dechargementConteneur( @PathVariable("idConteneur") int idConteneur, @RequestParam String date ) throws ParseException {
		
		return conteneurService.dechargementConteneur(idConteneur, date);
		
	}
	
}
