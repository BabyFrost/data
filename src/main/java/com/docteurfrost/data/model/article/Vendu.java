package com.docteurfrost.data.model.article;

public class Vendu implements ArticleState {
	
	private Article article;

	public Vendu(Article article) {
		this.article = article;
	}

	@Override
	public void vendre() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retourner() {
		article.setState( article.getEnVente() );
	}

	@Override
	public void avancer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deballer() {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

}
