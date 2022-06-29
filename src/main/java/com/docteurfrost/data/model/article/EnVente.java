package com.docteurfrost.data.model.article;

import com.docteurfrost.data.exception.BadRequestException;

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
		throw new BadRequestException("Retour Impossible");
	}

	@Override
	public void reserver() {
		article.setState( article.getReserve() );
	}

	@Override
	public void deballer() {
		throw new BadRequestException("Deballer Impossible");
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
