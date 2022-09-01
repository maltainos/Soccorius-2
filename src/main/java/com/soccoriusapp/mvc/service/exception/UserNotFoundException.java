package com.soccoriusapp.mvc.service.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends Exception {

	private String message;
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
		this.message = message;
	}
	
}
