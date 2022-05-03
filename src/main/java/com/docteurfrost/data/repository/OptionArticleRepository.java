package com.docteurfrost.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.docteurfrost.data.categorie.IdOptionArticle;
import com.docteurfrost.data.categorie.OptionArticle;

public interface OptionArticleRepository extends CrudRepository < OptionArticle, IdOptionArticle > {

}
