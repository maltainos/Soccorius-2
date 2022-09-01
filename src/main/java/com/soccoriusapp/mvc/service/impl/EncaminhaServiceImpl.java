package com.soccoriusapp.mvc.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.soccoriusapp.mvc.entity.Paciente;
import com.soccoriusapp.mvc.service.EncaminhadoService;

@Service
public class EncaminhaServiceImpl implements EncaminhadoService{

	@Override
	public Long countEncaminhados() {
		// TODO Auto-generated method stub
		return 0l;
	}

	@Override
	public Paciente createEncaminhado(Paciente paciente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Paciente> searchEncaminhados(int page, int limit, String sortColumn, String sortMode, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
