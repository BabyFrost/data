package com.docteurfrost.data.model.article;

public class DansConteneur implements ArticleState {
	
	private Article article;

	public DansConteneur(Article article) {
		this.article = article;
	}

	@Override
	public void vendre() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void retourner() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void avancer() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deballer() {
		
		
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

	@Override
	public void decharger() {
		article.setState( article.getEnMagasin() );	
	}

}
