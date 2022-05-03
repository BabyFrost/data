package com.docteurfrost.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.docteurfrost.data.categorie.Categorie;
import com.docteurfrost.data.model.article.Article;

@Repository
public interface ArticleRepository extends CrudRepository < Article, String > {

	Iterable<Article> findAllByCategorie(Categorie categorie);

//	Optional<Article> findByNom(String nom);

}
