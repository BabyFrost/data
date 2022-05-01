package com.docteurfrost.data.model.article;

import javax.persistence.AttributeConverter;

public class ArticleStateToStringConverter implements AttributeConverter<ArticleState,String> {

	@Override
	public String convertToDatabaseColumn(ArticleState state) {
		
		return state.toString();
		
	}

	@Override
	public ArticleState convertToEntityAttribute(String state) {
		
		switch(state) {
			case "Disparu":
				return new Disparu(null);
			case "EnMagasin":
				return new EnMagasin(null);
			case "EnVente":
				return new EnVente(null);
			case "Reserve":
				return new Reserve(null);
			case "Vendu":
				return new Vendu(null);
		}
		
		return null;
	}

}
