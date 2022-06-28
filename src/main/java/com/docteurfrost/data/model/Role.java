package com.docteurfrost.data.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="T_ROLE")
public class Role {
	
	@Id
	@Column(name="NOM")
	private String nom;
	
	@OneToMany(mappedBy = "role", cascade=CascadeType.ALL)
	@JsonManagedReference(value="utilisateur_role")
	private List<Utilisateur> utilisateurs = new ArrayList<>();

}
