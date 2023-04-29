package com.soccoriusapp.mvc.service;

import org.springframework.data.domain.Page;

import com.soccoriusapp.mvc.entity.ConsultaEntity;

public interface ReportService {
	
	public Page<ConsultaEntity> getReportPerPage(int page, int limit, String doenca, String bairro);

}
