package com.docteurfrost.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.repository.VenteRepository;

@Service
public class VenteService {
	
	@Autowired
	private VenteRepository venteRepository;

}
