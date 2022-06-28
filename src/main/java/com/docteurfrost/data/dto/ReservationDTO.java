package com.docteurfrost.data.dto;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.docteurfrost.data.dto.interne.ArticleInterneDTO;
import com.docteurfrost.data.dto.interne.AvanceInterneDTO;
import com.docteurfrost.data.dto.interne.ClientInterneDTO;
import com.docteurfrost.data.dto.interne.VendeurInterneDTO;
import com.docteurfrost.data.model.Avance;
import com.docteurfrost.data.model.reservation.Reservation;
import com.docteurfrost.data.tools.DateStringConverter;

public class ReservationDTO {
	
	private int id;
	private String libelle;
	private String date;
	private ClientInterneDTO client;
	private ArticleInterneDTO article;
	private VendeurInterneDTO vendeur;
	private List<AvanceInterneDTO> avances = new ArrayList<>();
	private int montantAvance;
	private String status;
	
	public ReservationDTO() {}
	
	public ReservationDTO( Reservation reservation ) {
		
		this.id = reservation.getId();
		this.libelle = reservation.getLibelle();
		this.date = DateStringConverter.dateToString( reservation.getDate() );
		this.client = new ClientInterneDTO( reservation.getClient() );
		this.article = new ArticleInterneDTO( reservation.getArticle() );
		this.vendeur = new VendeurInterneDTO( reservation.getVendeur() );
		
		AvanceInterneDTO avanceDTO;
		List<Avance> avances =  reservation.getAvances();
		for ( int i=0; i < avances.size(); i++ ) {
			avanceDTO = new AvanceInterneDTO( avances.get(i) );
			this.avances.add( avanceDTO );
		}
		
		this.montantAvance = reservation.getMontantAvance();
		this.status = reservation.getState().toString();
		
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Date getDate() throws ParseException {
		return DateStringConverter.stringToDate( this.date );
	}

	public void setDate(Date date) {
		this.date = DateStringConverter.dateToString( date );
	}

	public ClientInterneDTO getClient() {
		return client;
	}

	public void setClient(ClientInterneDTO client) {
		this.client = client;
	}

	public ArticleInterneDTO getArticle() {
		return article;
	}

	public void setArticle(ArticleInterneDTO article) {
		this.article = article;
	}

	public VendeurInterneDTO getVendeur() {
		return vendeur;
	}

	public void setVendeur(VendeurInterneDTO vendeur) {
		this.vendeur = vendeur;
	}

	public List<AvanceInterneDTO> getAvances() {
		return avances;
	}

	public void setAvances(List<AvanceInterneDTO> avances) {
		this.avances = avances;
	}

	public int getMontantAvance() {
		return montantAvance;
	}

	public void setMontantAvance(int montantAvance) {
		this.montantAvance = montantAvance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
