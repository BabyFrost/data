package com.docteurfrost.data.model.article;

import com.docteurfrost.data.exception.BadRequestException;

public class DansConteneur implements ArticleState {
	
	private Article article;

	public DansConteneur(Article article) {
		this.article = article;
	}

	@Override
	public void vendre() {
		throw new BadRequestException("Vente Impossible");
	}

	@Override
	public void retourner() {
		throw new BadRequestException("Retour Impossible");
	}

	@Override
	public void reserver() {
		throw new BadRequestException("Reservation Impossible");	
	}

	@Override
	public void deballer() {
		throw new BadRequestException("Deballage Impossible");
	}
	
	public String toString() {
		return this.getClass().getSimpleName();
	}

	@Override
	public void decharger() {
		article.setState( article.getEnMagasin() );	
	}

	@Override
	public void rembourserReservation() {
		throw new BadRequestException("Remboursement Impossible");
	}

}
