package com.docteurfrost.data.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<PanierDTO> getAllPaniersDTO( @RequestParam(required = false) String id ) throws BadRequestException, NumberFormatException, ResourceNotFoundException {
		
		if( id == null) { id = "0"; }
		if ( StringUtils.isNumeric(id) ) {
			List<Panier> paniers = panierService.getAllPanier( Integer.valueOf(id) );
			List<PanierDTO> paniersDTO = new ArrayList<>();
			for (int i=0; i<paniers.size(); i++) {
				paniersDTO.add( new PanierDTO( paniers.get(i) ) );
			}
			
			return paniersDTO;
		} else {
			throw new BadRequestException("Bad Parameter");
		}
		
	}
	
}
