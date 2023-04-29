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
public class TriagemRest {
	
	private LocalDate data;
	private Long totalData;

}
