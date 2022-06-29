package com.docteurfrost.data.model.reservation;

import javax.persistence.AttributeConverter;

public class ReservationStateToStringConverter implements AttributeConverter<ReservationState,String> {

	@Override
	public String convertToDatabaseColumn(ReservationState state) {
		
		return state.toString();
		
	}

	@Override
	public ReservationState convertToEntityAttribute(String state) {
		
		switch(state) {
			case "Abandonne":
				return new Abandonne(null);
			case "Complet":
				return new Complet(null);
			case "Partiel":
				return new Partiel(null);
			case "Rembourse":
				return new Rembourse(null);
		}
		
		return null;
	}

}
