package com.docteurfrost.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docteurfrost.data.repository.OptionArticleRepository;

@Service
public class OptionArticleService {
	
	@Autowired
	private OptionArticleRepository optionArticleRepository;

}