package com.soccoriusapp.mvc.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TransfereniaNotFoundException extends RuntimeException {
	
	private String message;
	private static final long serialVersionUID = 1L;

	public TransfereniaNotFoundException(String message) {
		this.message = message;
	}
}
