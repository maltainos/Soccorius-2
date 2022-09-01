package com.soccoriusapp.mvc.service;

import org.springframework.data.domain.Page;

import com.soccoriusapp.mvc.entity.InternadoEntity;
import com.soccoriusapp.mvc.entity.Paciente;
import com.soccoriusapp.mvc.entity.UserEntity;

public interface InternadoService {
	
	Long countInternados();
	Paciente createInternado(InternadoEntity internado);
	Page<Paciente> searchInternados(int page, int limit, String sortColumn, String sortMode, String keyword);
	Page<InternadoEntity> getInternados(int page, int limit, String sortColumn, String sortMode);
	InternadoEntity internar(UserEntity user, InternadoEntity internado);
}
