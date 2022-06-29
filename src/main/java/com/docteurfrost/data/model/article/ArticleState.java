package com.docteurfrost.data.model.article;

public interface ArticleState {
	
	public abstract void vendre();
	public abstract void retourner();
	public abstract void reserver();
	public abstract void deballer();
	public abstract void decharger();
	public abstract void rembourserReservation();

}
