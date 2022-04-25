package com.docteurfrost.data.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.docteurfrost.data.model.Categorie;
import com.docteurfrost.data.model.article.Article;

@Repository
public interface ArticleRepository extends CrudRepository < Article, Integer > {

	Iterable<Article> findAllByCategorie(Categorie categorie);

	Optional<Article> findByNom(String nom);

}
