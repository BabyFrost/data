package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.dto.PanierDTO;
import com.docteurfrost.data.exception.BadRequestException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Panier;
import com.docteurfrost.data.service.PanierService;

@RequestMapping("/paniers")
@RestController
public class PanierController {

	@Autowired
	private PanierService panierService;
	
	@GetMapping()
	@ResponseBody
	public List<PanierDTO> getAllPaniersDTO(  ) throws BadRequestException, NumberFormatException, ResourceNotFoundException {

		List<Panier> paniers = panierService.getAllPanier();
		List<PanierDTO> paniersDTO = new ArrayList<>();
		for (int i=0; i<paniers.size(); i++) {
			paniersDTO.add( new PanierDTO( paniers.get(i) ) );
		}
		
		return paniersDTO;
		
	}
	
	@GetMapping("/{idPanier}")
	@ResponseBody
	public PanierDTO getPanierById( @PathVariable("idPanier") int idPanier ) throws ResourceNotFoundException {
		
		Panier panier = panierService.getPanierById(idPanier);
		return new PanierDTO( panier );
		
	}
	
}
