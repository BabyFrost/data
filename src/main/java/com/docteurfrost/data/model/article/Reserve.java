package com.docteurfrost.data.model.article;

public class Reserve implements ArticleState {
	
	private Article article;

	public Reserve(Article article) {
		this.article = article;
	}

	@Override
	public void vendre() {
		article.setState( article.getVendu() );
	}

	@Override
	public void retourner() {
		article.setState( article.getEnVente() );
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

	@Override
	public void decharger() {
		// TODO Auto-generated method stub
		
	}

}
