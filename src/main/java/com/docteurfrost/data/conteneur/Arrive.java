package com.docteurfrost.data.conteneur;

import java.util.ArrayList;
import java.util.List;

import com.docteurfrost.data.model.article.Article;

public class Arrive implements ConteneurState {
	
	private Conteneur conteneur;
	
	public Arrive() { }
	
	public Arrive(Conteneur conteneur) {
		this.conteneur = conteneur;
	}

	@Override
	public void depart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void arrive() {
		// TODO Auto-generated method stub
		
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
