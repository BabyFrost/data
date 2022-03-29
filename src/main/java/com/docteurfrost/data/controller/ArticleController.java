package com.docteurfrost.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.docteurfrost.data.model.Article;
import com.docteurfrost.data.model.Categorie;
import com.docteurfrost.data.repository.ArticleRepository;
import com.docteurfrost.data.repository.CategorieRepository;

@RestController
public class ArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;

	@GetMapping("/article")
	@ResponseBody
	public Iterable<Article> getMatieresByEnseignant( ) {
		return articleRepository.findAll();
	}
	
	
	@GetMapping("/article/{categorie}")
	@ResponseBody public Iterable<Article>
	getArticlesByCategorie( @PathVariable("categorie") String nomCategorie ) {
		Categorie categorie;
		  
		try {
			categorie = categorieRepository.findById(nomCategorie).get();
		} finally {
		} 

		return articleRepository.findAllByCategorie(categorie);	  
	}
	 
}
