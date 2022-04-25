package com.docteurfrost.data.model.article;

public class EnVente implements ArticleState {
	
	private Article article;

	public EnVente(Article article) {
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
		
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
