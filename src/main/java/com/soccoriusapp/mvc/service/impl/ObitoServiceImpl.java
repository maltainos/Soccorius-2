package com.soccoriusapp.mvc.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.soccoriusapp.mvc.entity.Paciente;
import com.soccoriusapp.mvc.service.ObitoService;

@Service
public class ObitoServiceImpl implements ObitoService {

	@Override
	public Long countObitos() {
		// TODO Auto-generated method stub
		return 0l;
	}

	@Override
	public Paciente createObito(Paciente paciente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Paciente> searchObitos(int page, int limit, String sortColumn, String sortMode, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
