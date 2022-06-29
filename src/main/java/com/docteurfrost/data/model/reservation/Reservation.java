package com.docteurfrost.data.model.reservation;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.docteurfrost.data.model.Avance;
import com.docteurfrost.data.model.Client;
import com.docteurfrost.data.model.Operation;
import com.docteurfrost.data.model.Utilisateur;
import com.docteurfrost.data.model.article.Article;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_RESERVATIONS")
public class Reservation extends Operation {
	
	@OneToMany(mappedBy = "reservation", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="avances_reservation")
	private List<Avance> avances = new ArrayList<>();

	@Column(name="MONTANT_AVANCE")
	private int totalAvances;
	
	@Transient ReservationState abandonne;
	@Transient ReservationState complet;
	@Transient ReservationState partiel;
	@Transient ReservationState rembourse;
	
	@Column(name="ETAT")
	@Convert(converter = ReservationStateToStringConverter.class )
	private ReservationState state;
	
	public Reservation() {
		abandonne = new Abandonne( this );
		complet = new Complet( this );
		partiel = new Partiel( this );
		rembourse = new Rembourse( this );
		
		state = partiel;
	}
	
	public Reservation ( String libelle, Client client, Article article, Utilisateur utilisateur, int totalAvances ) {
		super( libelle, client, article, utilisateur);
		
		this.totalAvances = totalAvances;
		
		abandonne = new Abandonne( this );
		complet = new Complet( this );
		partiel = new Partiel( this );
		rembourse = new Rembourse( this );
		
		state = partiel;
	}
	
	@PostLoad
	public void stateInit() {
		
		switch( state.toString() ) {
			case "Abandonne":
				this.state = abandonne;
				break;
			case "Complet":
				this.state = complet;
				break;
			case "Partiel":
				this.state = partiel;
				break;
			case "Rembourse":
				this.state = rembourse;
				break;
		}
		
	}

	public List<Avance> getAvances() {
		return avances;
	}

	public void setAvances(List<Avance> avances) {
		this.avances = avances;
	}

	public int getTotalAvances() {
		return totalAvances;
	}

	public void setTotalAvances(int totalAvances) {
		this.totalAvances = totalAvances;
	}

	public ReservationState getAbandonne() {
		return abandonne;
	}

	public void setAbandonne(ReservationState abandonne) {
		this.abandonne = abandonne;
	}

	public ReservationState getComplet() {
		return complet;
	}

	public void setComplet(ReservationState complet) {
		this.complet = complet;
	}

	public ReservationState getPartiel() {
		return partiel;
	}

	public void setPartiel(ReservationState partiel) {
		this.partiel = partiel;
	}

	public ReservationState getRembourse() {
		return rembourse;
	}

	public void setRembourse(ReservationState rembourse) {
		this.rembourse = rembourse;
	}

	public ReservationState getState() {
		return state;
	}

	public void setState(ReservationState state) {
		this.state = state;
	}
	
	public void abandonner() {
		state.abandonner();
	}
	
	public int avancer( int montant ) {
		System.out.println("Inside Reservation.avancer");
		int prixArticle = super.getArticle().getPrix();
		if ( ( montant + this.totalAvances ) >= prixArticle  ) {
			montant = prixArticle - totalAvances;
			state.avancer( montant );
			this.completer();
		} else {
			System.out.println("Reservation.Avancer : state.avancer");
			System.out.println("Reservation.Avancer : state = "+this.state.toString() );
			state.avancer( montant );
		}
		
		return montant;
	}
	
	public void completer() {
		state.completer();
	}
	
	public void rembourser() {
		state.rembourser();
	}
	
}
