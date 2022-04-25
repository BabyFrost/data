package com.docteurfrost.data.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_RETOURS")
public class Retour extends Operation {
	
//	@ManyToOne
	
	@OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "VENTE", referencedColumnName = "ID")
	@JsonManagedReference(value="retour_vente")
	private Vente vente;

	public Retour() { }

	public Retour(String libelle, Utilisateur vendeur, Vente vente) {
		super(libelle, vente.getClient(), vente.getArticle(), vendeur);
		this.vente = vente;
	}

	public Vente getVente() {
		return vente;
	}

	public void setVente(Vente vente) {
		this.vente = vente;
	}	

}
