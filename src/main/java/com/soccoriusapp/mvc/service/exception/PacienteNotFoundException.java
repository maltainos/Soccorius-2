package com.soccoriusapp.mvc.service.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PacienteNotFoundException extends RuntimeException {
	
	private String message;
	private static final long serialVersionUID = 1L;

	public PacienteNotFoundException(String message) {
		super(message);
		this.message = message;
	}
}
