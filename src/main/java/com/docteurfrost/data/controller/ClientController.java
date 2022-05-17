package com.docteurfrost.data.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.dto.ClientDTO;
import com.docteurfrost.data.model.Client;
import com.docteurfrost.data.repository.ClientRepository;
import com.docteurfrost.data.tools.DateStringConverter;

@RequestMapping("/clients")
@RestController
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@GetMapping()
	@ResponseBody
	public Iterable<ClientDTO> getAllVentes( ) {
		
		System.out.println( "Get Clients" );
		
		List<Client> clients = new ArrayList<>();
		clientRepository.findAll().forEach(clients::add);
		
		List<ClientDTO> clientsDTO = new ArrayList<>();
		for (int i=0; i<clients.size(); i++) {
			clientsDTO.add( new ClientDTO( clients.get(i) ) );
		}
		
		return clientsDTO;
	}

	@PostMapping()
	public ResponseEntity<String> saveClient( @Valid @RequestBody ClientDTO clientDTO ) throws ParseException {
		System.out.println("Ca entre");
		
		System.out.println( " Received Save Client Request" );
		
		if ( clientRepository.findById( clientDTO.getTelephone() ).isPresent() ) {
			return new ResponseEntity<>( "Ce client existe deja", HttpStatus.CONFLICT );
		}
		
		Client client = new Client( clientDTO.getNom(), clientDTO.getPrenom(), DateStringConverter.stringToDate( clientDTO.getDateDeNaissance() ), clientDTO.getNumeroCNI(), clientDTO.getEmail(), clientDTO.getTelephone(), clientDTO.getSexe() );
		clientRepository.save(client);
		
		return new ResponseEntity<>( "Client cree", HttpStatus.CREATED );
	}
	
}
