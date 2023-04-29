package com.soccoriusapp.mvc.model.rest;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TriagemReceitaPacienteRest {

	private String medicamentos;
	
	private String pacienteCode;

	private String firstName;

	private String lastName;

	private LocalDate dateOfBirth;
	
	private String address;

	private int triagemNumber;

	private String sintomas;

	private String doenca;

	private LocalDate createAt;

}
