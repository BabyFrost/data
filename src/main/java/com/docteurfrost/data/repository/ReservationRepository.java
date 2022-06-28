package com.docteurfrost.data.repository;

import org.springframework.data.repository.CrudRepository;

import com.docteurfrost.data.model.reservation.Reservation;

public interface ReservationRepository extends CrudRepository< Reservation, Integer> {

}
