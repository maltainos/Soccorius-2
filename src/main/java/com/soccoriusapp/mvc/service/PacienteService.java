package com.soccoriusapp.mvc.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import com.soccoriusapp.mvc.entity.Paciente;
import com.soccoriusapp.mvc.model.rest.ReportIncomeWeklyRest;

public interface PacienteService {

	Long countPacientes();
	List<Paciente> getPacientes();
	Paciente createPaciente(Paciente paciente);
	Page<Paciente> searchPacientes(int page, int limit, String sortColumn, String sortMode, String keyword);
	List<ReportIncomeWeklyRest> getWeeklyIncome(LocalDate startAt, LocalDate endAt);
	
}
