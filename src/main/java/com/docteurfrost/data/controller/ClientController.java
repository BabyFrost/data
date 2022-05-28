package com.docteurfrost.data.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.dto.ClientDTO;
import com.docteurfrost.data.model.Client;
import com.docteurfrost.data.service.ClientService;

@RequestMapping("/clients")
@RestController
public class ClientController {
	
	@Autowired
    private ClientService clientService;
	
	@GetMapping()
	@ResponseBody
	public List<ClientDTO> getAllClientsDTO( @RequestParam(required = false) String telephone ) {
		
		List<Client> clients = clientService.getAllClients( telephone );
		List<ClientDTO> clientsDTO = new ArrayList<>();
		for (int i=0; i<clients.size(); i++) {
			clientsDTO.add( new ClientDTO( clients.get(i) ) );
		}
		
		return clientsDTO;	
	}

	@PostMapping()
	public ResponseEntity<String> saveClientDTO( @Valid @RequestBody ClientDTO clientDTO ) throws ParseException {			
		return clientService.saveClientDTO(clientDTO);
	}
	
}
