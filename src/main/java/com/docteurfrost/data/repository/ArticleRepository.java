package com.docteurfrost.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.model.article.ArticleState;
import com.docteurfrost.data.model.categorie.Categorie;
import com.docteurfrost.data.model.conteneur.Conteneur;

@Repository
public interface ArticleRepository extends CrudRepository < Article, String > {

	Iterable<Article> findAllByCategorie(Categorie categorie);

	Iterable<Article> findAllByConteneur(Conteneur conteneur);

	Iterable<Article> findAllByCategorieAndConteneur(Categorie categorie, Conteneur conteneur);

	Iterable<Article> findAllByState( ArticleState state );

//	Optional<Article> findByNom(String nom);

}
