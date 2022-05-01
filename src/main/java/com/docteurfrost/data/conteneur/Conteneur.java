package com.docteurfrost.data.conteneur;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.docteurfrost.data.model.article.Article;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_CONTENEURS")
public class Conteneur {
	
	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	
	@Column(name="NOM")
	private String nom;
	
	@Column(name="PAYS")
	private String pays;
	
	@Column(name="DEPART")
	private Date depart;
	
	@Column(name="ARRIVEE")
	private Date arrivee;
	
	@Column(name="DECHARGEMENT")
	private Date dechargement;
	
	@OneToMany(mappedBy = "conteneur", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="conteneur_article")
	private Collection<Article> articles = new ArrayList<>();
	
	@Transient ConteneurState arrive;
	@Transient ConteneurState chargement;
	@Transient ConteneurState decharge;
	@Transient ConteneurState enRoute;
	
	@Column(name="ETAT")
	@Convert(converter = ConteneurStateToStringConverter.class )
	private ConteneurState state = chargement;
	
	public Conteneur() { 
		arrive = new Arrive( this );
		chargement = new Chargement( this );
		decharge = new Decharge( this );
		enRoute = new EnRoute( this );
	}
	
	public Conteneur( int id, String nom, String pays, Date depart, Date arrivee, Date dechargement) {
		this.id = id;
		this.nom = nom;
		this.pays = pays;
		this.depart = depart;
		this.arrivee = arrivee;
		this.dechargement = dechargement;
		
		arrive = new Arrive( this );
		chargement = new Chargement( this );
		decharge = new Decharge( this );
		enRoute = new EnRoute( this );
		
		state = chargement;
	}
	
	@PostLoad
	public void stateInit() {
		
		switch( state.toString() ) {
			case "Arrive":
				this.state = arrive;
				break;
			case "Chargement":
				this.state = chargement;
				break;
			case "Decharge":
				this.state = decharge;
				break;
			case "EnRoute":
				this.state = enRoute;
				break;
		}
		
	}
	
	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Collection<Article> getArticles() {
		return articles;
	}

	public void setArticles(Collection<Article> articles) {
		this.articles = articles;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public Date getDepart() {
		return depart;
	}

	public void setDepart(Date depart) {
		this.depart = depart;
	}

	public Date getArrivee() {
		return arrivee;
	}

	public void setArrivee(Date arrivee) {
		this.arrivee = arrivee;
	}

	public Date getDechargement() {
		return dechargement;
	}

	public void setDechargement(Date dechargement) {
		this.dechargement = dechargement;
	}

	public ConteneurState getState() {
		return state;
	}

	public void setState(ConteneurState state) {
		this.state = state;
	}

	public ConteneurState getArrive() {
		return arrive;
	}

	public void setArrive(ConteneurState arrive) {
		this.arrive = arrive;
	}

	public ConteneurState getChargement() {
		return chargement;
	}

	public void setChargement(ConteneurState chargement) {
		this.chargement = chargement;
	}

	public ConteneurState getDecharge() {
		return decharge;
	}

	public void setDecharge(ConteneurState decharge) {
		this.decharge = decharge;
	}

	public ConteneurState getEnRoute() {
		return enRoute;
	}

	public void setEnRoute(ConteneurState enRoute) {
		this.enRoute = enRoute;
	}
	
}
