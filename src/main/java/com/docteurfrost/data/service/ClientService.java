package com.docteurfrost.data.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.dto.ClientDTO;
import com.docteurfrost.data.model.Client;
import com.docteurfrost.data.repository.ClientRepository;
import com.docteurfrost.data.tools.DateStringConverter;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client getClientById( String telephone ) {
		
		Optional<Client> clientTmp = clientRepository.findById(telephone);
		
		//CLIENT DOES NOT EXIST EXCEPTION
		Client client = null;
		if ( clientTmp.isPresent() ) {
			client = clientTmp.get();
		}
		
		return client;
	}
	
	public List<Client> getAllClients( String telephone ) {
		
		List<Client> clients = new ArrayList<>();	
		if ( telephone != null ) {
			clients.add( getClientById( telephone ) ) ;
		} else {
			clientRepository.findAll().forEach(clients::add);
		}
		
		return clients;
	}
	
	public void saveClient( Client client ) throws ParseException {
		clientRepository.save(client);
	}

	public ResponseEntity<String> saveClientDTO( ClientDTO clientDTO ) throws ParseException {
		
		if ( clientRepository.findById( clientDTO.getTelephone() ).isPresent() ) {
			return new ResponseEntity<>( "Ce client existe deja", HttpStatus.CONFLICT );
		}
		
		Client client = new Client( clientDTO.getNom(), clientDTO.getPrenom(), DateStringConverter.stringToDate( clientDTO.getDateDeNaissance() ), clientDTO.getNumeroCNI(), clientDTO.getEmail(), clientDTO.getTelephone(), clientDTO.getSexe() );
		saveClient(client);
		
		return new ResponseEntity<>( "Client cree", HttpStatus.CREATED );
	}

}
