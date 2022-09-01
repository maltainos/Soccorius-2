package com.soccoriusapp.mvc.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.soccoriusapp.mvc.entity.ConsultaEntity;
import com.soccoriusapp.mvc.entity.Paciente;
import com.soccoriusapp.mvc.service.ConsultaService;

@Service
public class ConsultaServiceImpl implements ConsultaService{

	@Override
	public Long countConsultas() {
		// TODO Auto-generated method stub
		return 0l;
	}

	@Override
	public Paciente createConsulta(ConsultaEntity consulta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Paciente> searchConsultas(int page, int limit, String sortColumn, String sortMode, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
