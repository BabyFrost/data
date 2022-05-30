package com.docteurfrost.data.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.categorie.Categorie;
import com.docteurfrost.data.dto.MarqueDTO;
import com.docteurfrost.data.model.Marque;
import com.docteurfrost.data.repository.MarqueRepository;

@Service
public class MarqueService {
	
	@Autowired
	private MarqueRepository marqueRepository;
	
	public List<Marque> getAllContainers( ) {
		List<Marque> marques = new ArrayList<>();
		marqueRepository.findAll().forEach(marques::add);
		
		return marques;
	}
	
	public List<MarqueDTO> getAllContainersDTO( ) {
		List<Marque> marques = new ArrayList<>();
		marqueRepository.findAll().forEach(marques::add);
		
		List<MarqueDTO> marquesDTO = new ArrayList<>();
		for (int i=0; i<marques.size(); i++) {
			marquesDTO.add( new MarqueDTO( marques.get(i) ) );
		}
		
		return marquesDTO;
	}
	
	public void saveMarque( Marque marque) {
		marqueRepository.save(marque);
	}
	
	public ResponseEntity<String> saveMarqueDTO( MarqueDTO marqueDTO) {
		
		if ( marqueRepository.findById(marqueDTO.getNom()).isPresent() ) {
			return new ResponseEntity<>( "Cette Marque existe deja", HttpStatus.CONFLICT );
		}
		
		Marque marque = new Marque( marqueDTO.getNom(), marqueDTO.getLibelle() );
		saveMarque(marque);
		
		return new ResponseEntity<>( "Marque cree", HttpStatus.CREATED );
	}
	
	public ResponseEntity<String> updateMarqueDTO( MarqueDTO marqueDTO) {
		if ( marqueRepository.findById(marqueDTO.getNom()).isPresent() ) {
			Marque marque = marqueRepository.findById(marqueDTO.getNom()).get();
			marque.setNom( marqueDTO.getNom() );
			marque.setLibelle( marqueDTO.getLibelle() );
			saveMarque(marque);
			
			return new ResponseEntity<>( "Marque Editee", HttpStatus.OK );
		} else {
			return new ResponseEntity<>( "Cette marque n'existe pas", HttpStatus.BAD_REQUEST );
		}	
		
	}
	
	public ResponseEntity<String> addCategoriesMarque( String idMarque, List<Categorie> categories ) {
		
		Optional<Marque> marqueTmp = marqueRepository.findById( idMarque );
		Marque marque;
		if ( marqueTmp.isPresent() ) {
			marque = marqueTmp.get();
		} else {
			return new ResponseEntity<>( "Marque Inexistante", HttpStatus.ALREADY_REPORTED );
		}

		for (int i = 0; i<categories.size(); i++) {
			
			Categorie categorie = categories.get(i);
			marque.getCategories().add(categorie);
			marqueRepository.save( marque );
			
		}
		
		return new ResponseEntity<>( "Categories Enregistrees", HttpStatus.OK );
		
	}

}
