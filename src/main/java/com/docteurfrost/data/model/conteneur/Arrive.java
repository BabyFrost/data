package com.docteurfrost.data.model.conteneur;

import java.util.ArrayList;
import java.util.List;

import com.docteurfrost.data.exception.BadRequestException;
import com.docteurfrost.data.model.article.Article;

public class Arrive implements ConteneurState {
	
	private Conteneur conteneur;
	
	public Arrive() { }
	
	public Arrive(Conteneur conteneur) {
		this.conteneur = conteneur;
	}

	@Override
	public void depart() {
		throw new BadRequestException("Depart Impossible");
	}

	@Override
	public void arrive() {
		throw new BadRequestException("Arrive Impossible");
	}

	@Override
	public void decharger() {
		conteneur.setState( conteneur.getDecharge() );
		
		List<Article> articles = new ArrayList<>();
		conteneur.getArticles().forEach(articles::add);
		for ( int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			article.decharger();
		}
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
