package com.docteurfrost.data.model.article;

public class EnMagasin implements ArticleState {
	
	private Article article;

	public EnMagasin() { }
	
	public EnMagasin(Article article) {
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
		article.setState( article.getEnVente() );
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
