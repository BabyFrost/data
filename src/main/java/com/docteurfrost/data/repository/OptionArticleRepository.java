package com.docteurfrost.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.docteurfrost.data.model.IdOptionArticle;
import com.docteurfrost.data.model.OptionArticle;

public interface OptionArticleRepository extends CrudRepository < OptionArticle, IdOptionArticle > {

}
