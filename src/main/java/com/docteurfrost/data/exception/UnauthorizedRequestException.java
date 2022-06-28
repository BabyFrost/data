package com.docteurfrost.data.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedRequestException extends RuntimeException {
	
	public UnauthorizedRequestException(String message){
        super(message);
    }

}
