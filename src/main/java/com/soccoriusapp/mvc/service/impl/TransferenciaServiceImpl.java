package com.soccoriusapp.mvc.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.soccoriusapp.mvc.entity.Paciente;
import com.soccoriusapp.mvc.entity.TransferenciaEntity;
import com.soccoriusapp.mvc.entity.Triagem;
import com.soccoriusapp.mvc.repository.TransferenciaRepository;
import com.soccoriusapp.mvc.security.SoccoriusUserDetails;
import com.soccoriusapp.mvc.service.TransferenciaService;
import com.soccoriusapp.mvc.service.exception.TransfereniaNotFoundException;
import com.soccoriusapp.mvc.service.utils.Utility;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {
	
	@Autowired
	private Utility utility;
	
	@Autowired
	private TriagemServiceImpl triagemService;
	
	@Autowired
	private TransferenciaRepository transferenciaRepository;

	@Override
	public Long countTransferencias() {
		
		return transferenciaRepository.count();
	}

	@Override
	public TransferenciaEntity createTransferencias(SoccoriusUserDetails authUser, TransferenciaEntity transferencia) {
		transferencia.setDataTransferencia(LocalDateTime.now());
		transferencia.setTransferenciaId(utility.getGeneratedPacienteCode(8, 8));
		transferencia.setMedicoResponsavel(authUser.getUser());
		
		Triagem triagem = transferencia.getTriagem();
		triagemService.setFinish(Boolean.TRUE, triagem.getId());
		
		return transferenciaRepository.save(transferencia);
	}

	@Override
	public Page<Paciente> searchTransferencias(int page, int limit, String sortColumn, String sortMode,
			String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransferenciaEntity getTransferencia(Integer transferenciaId) {
		Optional<TransferenciaEntity> foundTransferencia = transferenciaRepository.findById(transferenciaId);
	
		if(!foundTransferencia.isPresent())
			throw new TransfereniaNotFoundException("Transferencia Not Found Exception");
		
		return foundTransferencia.get();
	}

	public Page<TransferenciaEntity> getTransferencias(int page, int limit, String sortColumn, String sortMode) {
		
		if(page > 0) page = page -1;
		
		Sort sort = Sort.by(sortColumn);
		
		sort = sortMode.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();
		
		Pageable pageable = PageRequest.of(page, limit, sort);
		
		return transferenciaRepository.findAll(pageable);
	}

}
