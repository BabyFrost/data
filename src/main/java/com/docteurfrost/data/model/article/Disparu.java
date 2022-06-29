package com.docteurfrost.data.model.article;

import com.docteurfrost.data.exception.BadRequestException;

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
		throw new BadRequestException("Retour Impossible");
	}

	@Override
	public void reserver() {
		article.setState( article.getReserve() );
	}

	@Override
	public void deballer() {
		article.setState( article.getEnMagasin() );
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

	@Override
	public void decharger() {
		throw new BadRequestException("Dechargement Impossible");
	}
	
	@Override
	public void rembourserReservation() {
		throw new BadRequestException("Remboursement Impossible");
	}

}
