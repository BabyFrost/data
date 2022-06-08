package com.docteurfrost.data.controller;

import java.text.ParseException;
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

import com.docteurfrost.data.dto.ClientDTO;
import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Client;
import com.docteurfrost.data.service.ClientService;
import com.docteurfrost.data.tools.DateStringConverter;

@RequestMapping("/clients")
@RestController
public class ClientController {
	
	@Autowired
    private ClientService clientService;
	
	@GetMapping("/{clientId}")
	@ResponseBody
	public ClientDTO getClientByIdDTO( @PathVariable("clientId") String clientId ) throws ResourceNotFoundException {
		return new ClientDTO( clientService.getClientById(clientId) );	
	}
	
	@GetMapping()
	@ResponseBody
	public List<ClientDTO> getAllClientsDTO() throws ResourceNotFoundException {
		
		List<Client> clients = clientService.getAllClient( );
		List<ClientDTO> clientsDTO = new ArrayList<>();
		for (int i=0; i<clients.size(); i++) {
			clientsDTO.add( new ClientDTO( clients.get(i) ) );
		}
		
		return clientsDTO;	
	}

	@PostMapping()
	public ResponseEntity<ClientDTO> saveClientDTO( @Valid @RequestBody ClientDTO clientDTO ) throws ResourceConflictException, ParseException {
		Client client = new Client( clientDTO.getNom(), clientDTO.getPrenom(), DateStringConverter.stringToDate( clientDTO.getDateDeNaissance() ), clientDTO.getNumeroCNI(), clientDTO.getEmail(), clientDTO.getTelephone(), clientDTO.getSexe() );
		ClientDTO responseClientDTO = new ClientDTO( clientService.createClient(client) );
		return new ResponseEntity<>( responseClientDTO, HttpStatus.OK );
	}
	
}
