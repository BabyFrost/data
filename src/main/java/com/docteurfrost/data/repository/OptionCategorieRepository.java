package com.docteurfrost.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.docteurfrost.data.model.OptionCategorie;

public interface OptionCategorieRepository extends CrudRepository < OptionCategorie, Integer >  {

	Optional<OptionCategorie> findByNom(String option);

}
