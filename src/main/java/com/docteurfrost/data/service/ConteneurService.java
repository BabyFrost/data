package com.docteurfrost.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.repository.ConteneurRepository;

@Service
public class ConteneurService {
	
	@Autowired
	private ConteneurRepository conteneurRepository;

}
