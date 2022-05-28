package com.docteurfrost.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.docteurfrost.data.categorie.Categorie;
import com.docteurfrost.data.conteneur.Conteneur;
import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.model.article.ArticleState;

@Repository
public interface ArticleRepository extends CrudRepository < Article, String > {

	Iterable<Article> findAllByCategorie(Categorie categorie);

	Iterable<Article> findAllByConteneur(Conteneur conteneur);

	Iterable<Article> findAllByCategorieAndConteneur(Categorie categorie, Conteneur conteneur);

	Iterable<Article> findAllByState( ArticleState state );

//	Optional<Article> findByNom(String nom);

}
