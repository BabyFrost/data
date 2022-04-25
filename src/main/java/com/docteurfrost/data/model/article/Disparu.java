package com.docteurfrost.data.model.article;

public class Disparu implements ArticleState {
	
	private Article article;

	public Disparu(Article article) {
		this.article = article;
	}

	@Override
	public void vendre() {
		article.setState( article.getVendu() );
	}

	@Override
	public void retourner() {
		
	}

	@Override
	public void avancer() {
		article.setState( article.getReserve() );
	}

	@Override
	public void deballer() {
		article.setState( article.getEnMagasin() );
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

	
	
}
