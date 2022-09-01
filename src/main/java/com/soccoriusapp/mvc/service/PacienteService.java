package com.soccoriusapp.mvc.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.soccoriusapp.mvc.entity.Paciente;

public interface PacienteService {

	Long countPacientes();
	List<Paciente> getPacientes();
	Paciente createPaciente(Paciente paciente);
	Page<Paciente> searchPacientes(int page, int limit, String sortColumn, String sortMode, String keyword);
	
}
