package com.docteurfrost.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.docteurfrost.data.model.Client;

public interface ClientRepository extends CrudRepository < Client, Integer > {

}
