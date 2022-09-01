package com.soccoriusapp.mvc.service.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TriagemNotFoundException extends RuntimeException {

	private String message;
	private static final long serialVersionUID = 1L;

	public TriagemNotFoundException(String message) {
		super(message);
	}
}
