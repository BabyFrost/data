package com.docteurfrost.data.dto;

public class MyResponseDTO {
	
	String text;
	
	public MyResponseDTO() { }
	
	public MyResponseDTO( String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	

}
