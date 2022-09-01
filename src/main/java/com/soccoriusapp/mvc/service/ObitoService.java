package com.soccoriusapp.mvc.service;

import org.springframework.data.domain.Page;

import com.soccoriusapp.mvc.entity.Paciente;

public interface ObitoService {

	Long countObitos();
	Paciente createObito(Paciente paciente);
	Page<Paciente> searchObitos(int page, int limit, String sortColumn, String sortMode, String keyword);
}
