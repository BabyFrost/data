package com.docteurfrost.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.docteurfrost.data.model.categorie.IdOptionArticle;
import com.docteurfrost.data.model.categorie.OptionArticle;

public interface OptionArticleRepository extends CrudRepository < OptionArticle, IdOptionArticle > {

}
