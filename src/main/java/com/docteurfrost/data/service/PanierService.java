package com.docteurfrost.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.repository.PanierRepository;

@Service
public class PanierService {
	
	@Autowired
	private PanierRepository panierRepository;

}
