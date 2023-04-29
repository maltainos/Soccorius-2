package com.soccoriusapp.mvc.service.impl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.soccoriusapp.mvc.entity.Paciente;
import com.soccoriusapp.mvc.model.rest.ReportIncomeWeklyRest;
import com.soccoriusapp.mvc.repository.PacienteRepository;
import com.soccoriusapp.mvc.service.PacienteService;
import com.soccoriusapp.mvc.service.exception.PacienteNotFoundException;
import com.soccoriusapp.mvc.service.utils.Utility;

@Service
@Transactional
public class PacienteServiceImpl implements PacienteService{
	
	@Autowired
	private Utility utils;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Override
	public Page<Paciente> searchPacientes(int page, int limit, String sortColumn, String sortMode, String keyword) {
		
		if(page >= 1) page = page - 1;
		
		Sort sort = Sort.by(sortColumn);
		
		sort = sortMode.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(page, limit, sort);
		
		Page<Paciente> pacientes = pacienteRepository.findAll(keyword, pageable);

		return pacientes;
	}

	@Override
	public Paciente createPaciente(Paciente paciente) {
		LocalDate today = LocalDate.now();
		int yearsOld = today.getYear() - paciente.getDateOfBirth().getYear(); 
		paciente.setYearsOld(yearsOld);
		paciente.setPacienteCode(utils.getGeneratedPacienteCode(4, 6));
		paciente.setCreatedOn(LocalDate.now());
		Paciente savePaciente = pacienteRepository.save(paciente);
		
		return savePaciente;
	}

	@Override
	public Long countPacientes() {
		return pacienteRepository.count();
	}

	public Paciente getPaciente(Integer id) {
		
		Optional<Paciente> foundPaciente = pacienteRepository.findById(id);
		
		if(!foundPaciente.isPresent()) throw new PacienteNotFoundException("Paciente não encontrado");
		
		return foundPaciente.get();
	}

	@Override
	public List<Paciente> getPacientes() {
		
		return (List<Paciente>) pacienteRepository.findAll();
	}

	@Override
	public List<ReportIncomeWeklyRest> getWeeklyIncome(LocalDate startAt, LocalDate endAt) {
		List<ReportIncomeWeklyRest> findValue = pacienteRepository.reportPerWeek(endAt, startAt);
		
		List<ReportIncomeWeklyRest> returnValue = new ArrayList<ReportIncomeWeklyRest>();
		returnValue.add(new ReportIncomeWeklyRest(DayOfWeek.SUNDAY.toString(),0l));
		returnValue.add(new ReportIncomeWeklyRest(DayOfWeek.MONDAY.toString(),0l));
		returnValue.add(new ReportIncomeWeklyRest(DayOfWeek.THURSDAY.toString(),0l));
		returnValue.add(new ReportIncomeWeklyRest(DayOfWeek.WEDNESDAY.toString(),0l));
		returnValue.add(new ReportIncomeWeklyRest(DayOfWeek.TUESDAY.toString(),0l));
		returnValue.add(new ReportIncomeWeklyRest(DayOfWeek.FRIDAY.toString(),0l));
		returnValue.add(new ReportIncomeWeklyRest(DayOfWeek.SATURDAY.toString(),0l));
		
		findValue.forEach(fValue -> {
			returnValue.forEach(rValue -> {
				if(rValue.getDayOfWeek().equalsIgnoreCase(fValue.getDayOfWeek())) {
					rValue.setIncomeQtd(fValue.getIncomeQtd());
				}
			});
		});
		return returnValue;
	}
	
	
	
	

}
