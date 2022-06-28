package com.docteurfrost.data.model.conteneur;

import javax.persistence.AttributeConverter;

public class ConteneurStateToStringConverter implements AttributeConverter<ConteneurState,String> {

	@Override
	public String convertToDatabaseColumn(ConteneurState state) {
		
		return state.toString();
		
	}

	@Override
	public ConteneurState convertToEntityAttribute(String state) {
		
		switch(state) {
			case "Arrive":
				return new Arrive(null);
			case "Chargement":
				return new Chargement(null);
			case "Decharge":
				return new Decharge(null);
			case "EnRoute":
				return new EnRoute(null);
		}
		
		return null;
	}

}
