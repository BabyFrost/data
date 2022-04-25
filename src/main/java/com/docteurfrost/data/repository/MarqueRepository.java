package com.docteurfrost.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.docteurfrost.data.model.Marque;

public interface MarqueRepository extends CrudRepository < Marque, Integer > {

	Optional<Marque> findByNom(String nom);

}
