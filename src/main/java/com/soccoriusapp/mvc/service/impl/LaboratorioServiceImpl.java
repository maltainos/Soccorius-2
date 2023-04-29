package com.soccoriusapp.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.soccoriusapp.mvc.entity.LaboratorioEntity;
import com.soccoriusapp.mvc.repository.LaboratorioRepository;
import com.soccoriusapp.mvc.service.LaboratorioService;

@Service
public class LaboratorioServiceImpl implements LaboratorioService{
	
	@Autowired
	private LaboratorioRepository laboratorioRepository;

	@Override
	public Long countLaboratorios() {
		// TODO Auto-generated method stub
		return laboratorioRepository.count();
	}

	@Override
	public LaboratorioEntity createLaboratorio(LaboratorioEntity laboratorio) {
		laboratorio.setLaboratorista(null);
		laboratorio.setResultadoAmostra(null);
		return laboratorioRepository.save(laboratorio);
	}

	@Override
	public Page<LaboratorioEntity> searchLaboratorios(int page, int limit, String sortColumn, String sortMode, String keyword) {
		
		if(page > 0) page = page - 1;
		Sort sort = Sort.by(sortColumn);
		sort = sortMode.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(page, limit, sort);
		

		Page<LaboratorioEntity> pageOfLaboratorio = laboratorioRepository.findAll(keyword,pageable);
		return pageOfLaboratorio;
	}

}
