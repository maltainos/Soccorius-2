package com.soccoriusapp.mvc.service;

import org.springframework.data.domain.Page;

import com.soccoriusapp.mvc.entity.Paciente;

public interface EncaminhadoService {

	Long countEncaminhados();
	Paciente createEncaminhado(Paciente paciente);
	Page<Paciente> searchEncaminhados(int page, int limit, String sortColumn, String sortMode, String keyword);
	
}

