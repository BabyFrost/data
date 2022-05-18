package com.docteurfrost.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.repository.OptionCategorieRepository;

@Service
public class OptionCategorieService {
	
	@Autowired
	private OptionCategorieRepository optionCategorieRepository;

}
