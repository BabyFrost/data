package com.docteurfrost.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.repository.CategorieRepository;

@Service
public class CategorieService {

	@Autowired
	private CategorieRepository categorieRepository;
	
}
