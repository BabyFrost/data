package com.docteurfrost.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.repository.AvanceRepository;

@Service
public class AvanceService {
	
	@Autowired
	private AvanceRepository avanceRepository;

}
