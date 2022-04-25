package com.docteurfrost.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.docteurfrost.data.model.Categorie;

public interface CategorieRepository extends CrudRepository < Categorie, Integer > {

	Optional<Categorie> findByNom(String nomCategorie);

}
