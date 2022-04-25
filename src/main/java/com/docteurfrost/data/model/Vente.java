package com.docteurfrost.data.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.docteurfrost.data.model.article.Article;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="T_VENTES")
public class Vente extends Operation {
	
	@ManyToOne
	@JsonBackReference(value="panier_vente")
	@JoinColumn(name="PANIER")
	private Panier panier;
	
//	@OneToMany(mappedBy = "vente", cascade=CascadeType.REMOVE)
	@OneToOne(mappedBy = "vente")
	@JoinColumn(name="RETOUR")
	@JsonBackReference(value="retour_vente")
	private Retour retour;
	
	public Vente () { }
	
	public Vente ( String libelle, Client client, Article article, Utilisateur utilisateur, Panier panier ) {
		super( libelle, client, article, utilisateur);
		this.panier = panier;
	}

	public Panier getPanier() {
		return panier;
	}

	public Retour getRetour() {
		return retour;
	}

	public void setRetour(Retour retour) {
		this.retour = retour;
	}

}
