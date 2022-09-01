package com.soccoriusapp.mvc.service;

import org.springframework.data.domain.Page;

import com.soccoriusapp.mvc.entity.Paciente;
import com.soccoriusapp.mvc.entity.TransferenciaEntity;
import com.soccoriusapp.mvc.security.SoccoriusUserDetails;

public interface TransferenciaService {
	
	Long countTransferencias();
	TransferenciaEntity createTransferencias(SoccoriusUserDetails authUser ,TransferenciaEntity transferencia);
	Page<Paciente> searchTransferencias(int page, int limit, String sortColumn, String sortMode, String keyword);
	TransferenciaEntity getTransferencia(Integer transferenciaId);
}
