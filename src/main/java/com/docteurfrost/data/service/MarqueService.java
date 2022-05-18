package com.docteurfrost.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.repository.MarqueRepository;

@Service
public class MarqueService {
	
	@Autowired
	private MarqueRepository marqueRepository;

}
