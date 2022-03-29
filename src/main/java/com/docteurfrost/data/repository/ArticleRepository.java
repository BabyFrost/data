package com.docteurfrost.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.docteurfrost.data.model.Article;
import com.docteurfrost.data.model.Categorie;

@Repository
public interface ArticleRepository extends CrudRepository < Article, Integer > {

	Iterable<Article> findAllByCategorie(Categorie categorie);

}
