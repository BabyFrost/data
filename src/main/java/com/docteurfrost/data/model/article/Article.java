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
import javax.persistence.Transient;

import com.docteurfrost.data.conteneur.Conteneur;
import com.docteurfrost.data.model.Categorie;
import com.docteurfrost.data.model.Marque;
import com.docteurfrost.data.model.Operation;
import com.docteurfrost.data.model.OptionArticle;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_ARTICLE")
public class Article {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="ID")
//	private int id;
	
	@Id
	@Column(name="NOM")
	private String nom;
	
	@Column(name="LIBELLE")
	private String libelle;
	
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
	
	@Column(name="PRIX")
	private int prix;
	
	@Column(name="PHOTO")
	private String photo;
	
	@Column(name="DATE_AJOUT")
	private Date dateAjout;
	
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
	
	@Column(name="ETAT")
	@Convert(converter = ArticleStateToStringConverter.class )
	private ArticleState state = enMagasin;
	
	public Article() { 
		disparu = new Disparu( this );
		enMagasin = new EnMagasin( this );
		enVente = new EnVente( this );
		reserve = new Reserve( this );	
		vendu = new Vendu( this );
	}
	
	public Article( String nom, String libelle, Categorie categorie, Conteneur conteneur, int prixAchat, int prix, String photo) {
		this.nom = nom;
		this.libelle = libelle;
		this.categorie = categorie;
		this.conteneur = conteneur;
		this.prixAchat = prixAchat;
		this.prix = prix;
		this.photo = photo;
		this.dateAjout = new Date();
		
		disparu = new Disparu( this );
		enMagasin = new EnMagasin( this );
		enVente = new EnVente( this );
		reserve = new Reserve( this );
		vendu = new Vendu( this );
		
		state = enMagasin;	
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
		}
		
	}

//	public int getId() {
//		return id;
//	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
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

}
