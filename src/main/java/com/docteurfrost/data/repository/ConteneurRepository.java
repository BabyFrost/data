package com.docteurfrost.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.docteurfrost.data.model.Conteneur;

public interface ConteneurRepository extends CrudRepository < Conteneur, Integer > {

	Optional<Conteneur> findByNom(String conteneur);

}
