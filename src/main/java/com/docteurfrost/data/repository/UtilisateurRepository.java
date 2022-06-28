package com.docteurfrost.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.docteurfrost.data.model.Utilisateur;

public interface UtilisateurRepository extends CrudRepository < Utilisateur, Integer > {

	Optional<Utilisateur> findByUsername(String username);

}
