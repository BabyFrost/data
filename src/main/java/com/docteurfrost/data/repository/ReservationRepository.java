package com.docteurfrost.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.docteurfrost.data.model.Reservation;

public interface ReservationRepository extends CrudRepository< Reservation, Integer> {

}
