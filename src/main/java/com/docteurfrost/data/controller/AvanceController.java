package com.docteurfrost.data.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.dto.AvanceDTO;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Avance;
import com.docteurfrost.data.service.AvanceService;

@RequestMapping("/avances")
@RestController
public class AvanceController {
	
	@Autowired
	AvanceService avanceService;
	
	@GetMapping("/{idAvance}")
	@ResponseBody
	public AvanceDTO getAvanceById( @PathVariable("idAvance") int idAvance ) throws ResourceNotFoundException {
		return new AvanceDTO( avanceService.getAvanceById( idAvance ) );
	}
	
	@GetMapping()
	@ResponseBody
	public List<AvanceDTO> getAllAvances(  ) throws ResourceNotFoundException {
		
		List<Avance> avances = new ArrayList<>();	
		avances = avanceService.getAllAvance();
		
		List<AvanceDTO> avancesDTO = new ArrayList<>();
		for (int i=0; i<avances.size(); i++) {
			avancesDTO.add( new AvanceDTO( avances.get(i) ) );
		}
		
		return avancesDTO;
	}
	
	@PostMapping()
	@ResponseBody
	public ResponseEntity<AvanceDTO> getAvanceById( @Valid @RequestBody AvanceDTO avanceDTO ) throws ResourceNotFoundException {
		AvanceDTO responseAvanceDTO = new AvanceDTO( avanceService.getAvanceById( 1 ) );
		return new ResponseEntity<>( responseAvanceDTO, HttpStatus.OK );
	}

}
