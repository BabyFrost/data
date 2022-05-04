package com.docteurfrost.data.model.article;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.docteurfrost.data.categorie.Categorie;
import com.docteurfrost.data.categorie.OptionArticle;
import com.docteurfrost.data.conteneur.Conteneur;
import com.docteurfrost.data.model.Marque;
import com.docteurfrost.data.model.Operation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_ARTICLE")
public class Article {
	
	@Id
	@Column(name="NOM")
	private String nom;
	
	@Column(name="OBSERVATION")
	private String observation;
	
	@Column(name="NUMERO_DE_SERIE")
	private String numeroDeSerie;
	
	@ManyToOne
	@JsonBackReference(value="article_categorie")
	@JoinColumn(name="CATEGORIE")
	private Categorie categorie;
	
	@ManyToOne
	@JsonBackReference(value="conteneur_article")
	@JoinColumn(name="CONTENEUR")
	private Conteneur conteneur;
	
	@ManyToOne
	@JsonBackReference(value="marque_article")
	@JoinColumn(name="MARQUE")
	private Marque marque;
	
	@Column(name="PRIX_ACHAT")
	private int prixAchat;
	
	@Column(name="PRIX_LIQUIDATION")
	private int prixLiquidation;
	
	@Column(name="PRIX_ESTIMATIF")
	private int prixEstimatif;
	
	@Column(name="PRIX")
	private int prix;
	
	@Column(name="PHOTO")
	private String photo;
	
	@Column(name="ETAT")
	private String etat;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_ACHAT")
	private Date dateAchat;
	
	@Column(name="DATE_AJOUT")
	private Date dateSaisie;
	
	@Column(name="DATE_MISE_EN_VENTE")
	private Date dateMiseEnVente;
	
	@OneToMany(mappedBy = "article", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="article_options_article")
	private Collection<OptionArticle> options = new ArrayList<>();
	
	@OneToMany(mappedBy = "article", cascade=CascadeType.REMOVE)
	@JsonManagedReference(value="operations_article")
	private Collection<Operation> operations = new ArrayList<>();
	
	@Transient ArticleState disparu;
	@Transient ArticleState enMagasin;
	@Transient ArticleState enVente;
	@Transient ArticleState reserve;
	@Transient ArticleState vendu;
	@Transient ArticleState dansConteneur;
	
	@Column(name="STATUS")
	@Convert(converter = ArticleStateToStringConverter.class )
	private ArticleState state = dansConteneur;
	
	public Article() { 
		disparu = new Disparu( this );
		enMagasin = new EnMagasin( this );
		enVente = new EnVente( this );
		reserve = new Reserve( this );	
		vendu = new Vendu( this );
		dansConteneur = new DansConteneur( this );
	}
	
	public Article( String nom, String observation, String numeroDeSerie, Categorie categorie, Conteneur conteneur, Marque marque, int prixAchat, int prixLiquidation, int prixEstimatif, int prix, String photo, Date dateAchat) {
		this.nom = nom.toUpperCase();
		this.observation = observation;
		this.numeroDeSerie = numeroDeSerie;
		this.categorie = categorie;
		this.conteneur = conteneur;
		this.marque = marque;
		this.prixAchat = prixAchat;
		this.prixLiquidation =prixLiquidation;
		this.prixEstimatif = prixEstimatif;
		this.prix = prix;
		this.photo = photo;
		this.dateAchat = dateAchat;
		this.dateSaisie = new Date();
		this.dateMiseEnVente = null;
		
		System.out.println( this.dateSaisie );
		
		disparu = new Disparu( this );
		enMagasin = new EnMagasin( this );
		enVente = new EnVente( this );
		reserve = new Reserve( this );
		vendu = new Vendu( this );
		dansConteneur = new DansConteneur( this );
		
		state = dansConteneur;	
	}
	
	@PostLoad
	public void stateInit() {
		
		switch( state.toString() ) {
			case "Disparu":
				this.state = disparu;
				break;
			case "EnMagasin":
				this.state = enMagasin;
				break;
			case "EnVente":
				this.state = enVente;
				break;
			case "Reserve":
				this.state = reserve;
				break;
			case "Vendu":
				this.state = vendu;
				break;
			case "DansConteneur":
				this.state = dansConteneur;
				break;
		}
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom.toUpperCase();
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public Conteneur getConteneur() {
		return conteneur;
	}

	public void setConteneur(Conteneur conteneur) {
		this.conteneur = conteneur;
	}

	public int getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(int prixAchat) {
		this.prixAchat = prixAchat;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getDateSaisie() {
		return dateSaisie;
	}

	public void setDateSaisie(Date dateSaisie) {
		this.dateSaisie = dateSaisie;
	}

	public Date getDateMiseEnVente() {
		return dateMiseEnVente;
	}

	public void setDateMiseEnVente(Date dateMiseEnVente) {
		this.dateMiseEnVente = dateMiseEnVente;
	}

	public Collection<OptionArticle> getOptions() {
		return options;
	}

	public void setOptions(Collection<OptionArticle> options) {
		this.options = options;
	}
	
	public void avancer() {
		state.avancer();
	}
	
	public void deballer() {
		state.deballer();
	}
	
	public void decharger() {
		state.decharger();
	}
	
	public void retourner() {
		state.retourner();
	}
	
	public void vendre() {
		state.vendre();
	}
	
	public ArticleState getState() {
		return state;
	}
	
	public void setState( ArticleState state ) {
		this.state = state;
	}

	public Marque getMarque() {
		return marque;
	}

	public void setMarque(Marque marque) {
		this.marque = marque;
	}

	public ArticleState getDisparu() {
		return disparu;
	}

	public void setDisparu(ArticleState disparu) {
		this.disparu = disparu;
	}

	public ArticleState getEnMagasin() {
		return enMagasin;
	}

	public void setEnMagasin(ArticleState enMagasin) {
		this.enMagasin = enMagasin;
	}

	public ArticleState getEnVente() {
		return enVente;
	}

	public void setEnVente(ArticleState enVente) {
		this.enVente = enVente;
	}

	public ArticleState getReserve() {
		return reserve;
	}

	public void setReserve(ArticleState reserve) {
		this.reserve = reserve;
	}

	public ArticleState getVendu() {
		return vendu;
	}

	public void setVendu(ArticleState vendu) {
		this.vendu = vendu;
	}

	public ArticleState getDansConteneur() {
		return dansConteneur;
	}

	public void setDansConteneur(ArticleState dansConteneur) {
		this.dansConteneur = dansConteneur;
	}

	public int getPrixLiquidation() {
		return prixLiquidation;
	}

	public void setPrixLiquidation(int prixLiquidation) {
		this.prixLiquidation = prixLiquidation;
	}

	public int getPrixEstimatif() {
		return prixEstimatif;
	}

	public void setPrixEstimatif(int prixEstimatif) {
		this.prixEstimatif = prixEstimatif;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}

}
