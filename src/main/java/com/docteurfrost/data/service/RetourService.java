package com.docteurfrost.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.repository.RetourRepository;

@Service
public class RetourService {

	@Autowired
	private RetourRepository retourRepository;
	
}
