package com.POC.demoProject.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException() {
	}

	public ResourceNotFoundException(String message) {
		super(message)	;
		System.out.println(message);
		
}
	public ResourceNotFoundException(String message,Throwable cause) {
		super(message,cause);
		
}

}
