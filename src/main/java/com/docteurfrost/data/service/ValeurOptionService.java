package com.docteurfrost.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.repository.ValeurOptionRepository;

@Service
public class ValeurOptionService {

	@Autowired
	private ValeurOptionRepository valeurOptionRepository;
	
}
