package com.docteurfrost.data.model.article;

import com.docteurfrost.data.exception.BadRequestException;

public class Vendu implements ArticleState {
	
	private Article article;

	public Vendu(Article article) {
		this.article = article;
	}

	@Override
	public void vendre() {
		throw new BadRequestException("Vente Impossible");
	}

	@Override
	public void retourner() {
		article.setState( article.getEnVente() );
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
		throw new BadRequestException("Dechargement Impossible");
	}

	@Override
	public void rembourserReservation() {
		throw new BadRequestException("Remboursement Impossible");
	}
	
}
