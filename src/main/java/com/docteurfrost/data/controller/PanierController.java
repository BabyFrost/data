package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.docteurfrost.data.dto.PanierDTO;
import com.docteurfrost.data.model.Panier;
import com.docteurfrost.data.repository.PanierRepository;

@RequestMapping("/paniers")
@RestController
public class PanierController {

	@Autowired
	private PanierRepository panierRepository;
	
	@GetMapping()
	@ResponseBody
	public Iterable<PanierDTO> getAllPaniers( ) {
		List<Panier> paniers = new ArrayList<>();
		panierRepository.findAll().forEach(paniers::add);
		
		List<PanierDTO> paniersDTO = new ArrayList<>();
		for (int i=0; i<paniers.size(); i++) {
			paniersDTO.add( new PanierDTO( paniers.get(i) ) );
		}
		
		return paniersDTO;
	}
	
}
