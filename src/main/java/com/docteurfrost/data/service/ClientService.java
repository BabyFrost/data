package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.exception.ResourceConflictException;
import com.docteurfrost.data.exception.ResourceNotFoundException;
import com.docteurfrost.data.model.Client;
import com.docteurfrost.data.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client getClientById( String telephone ) throws ResourceNotFoundException {	
		return clientRepository.findById(telephone).orElseThrow( () -> new ResourceNotFoundException("No such Client !") );
	}
	
	public List<Client> getAllClient() throws ResourceNotFoundException {	
		List<Client> clients = new ArrayList<>();	
		clientRepository.findAll().forEach(clients::add);	
		return clients;
	}
	
	public Client saveClient( Client client ) {
		return clientRepository.save(client);
	}
	
	public Client createClient( Client client ) throws ResourceConflictException {
		
		Optional<Client> clientTmp = clientRepository.findById( client.getTelephone() );
		if ( clientTmp.isPresent() ) { throw new ResourceConflictException("Client existe deja"); }
		
		return saveClient( client );
	}

}
