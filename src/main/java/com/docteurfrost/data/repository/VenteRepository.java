package com.docteurfrost.data.repository;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.docteurfrost.data.model.Vente;
import com.docteurfrost.data.model.article.Article;

public interface VenteRepository extends CrudRepository < Vente, Integer > {

	Iterable<Vente> findAllByArticle(Article article);
	Iterable<Vente> findByDateBetween(Date dateDebut, Date dateFin);

}