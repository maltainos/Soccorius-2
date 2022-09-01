package com.soccoriusapp.mvc.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.soccoriusapp.mvc.entity.InternadoEntity;
import com.soccoriusapp.mvc.entity.Paciente;
import com.soccoriusapp.mvc.entity.Triagem;
import com.soccoriusapp.mvc.entity.UserEntity;
import com.soccoriusapp.mvc.repository.InternadoRepository;
import com.soccoriusapp.mvc.service.InternadoService;
import com.soccoriusapp.mvc.service.utils.Utility;

@Service
public class InternadoServiceImpl implements InternadoService {
	
	@Autowired
	private Utility utility;
	
	@Autowired
	private TriagemServiceImpl triagemService;
	
	@Autowired
	private InternadoRepository internadoRepository;

	@Override
	public Long countInternados() {
		return internadoRepository.count();
	}

	@Override
	public Paciente createInternado(InternadoEntity internado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Paciente> searchInternados(int page, int limit, String sortColumn, String sortMode, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<InternadoEntity> getInternados(int page, int limit, String sortColumn, String sortMode) {
		
		if (page > 0) page = page - 1;

		Sort sort = Sort.by(sortColumn);

		sort = sortMode.equalsIgnoreCase("asc") ? sort.ascending() : sort.descending();

		Pageable pageable = PageRequest.of(page, limit, sort);

		return internadoRepository.findAll(pageable);
	}

	@Override
	public InternadoEntity internar(UserEntity user, InternadoEntity internado) {
		
		internado.setMedicoResponsavel(user);
		internado.setInternadoId(utility.getGeneratedPacienteCode(3, 3));
		internado.setDataInternamento(LocalDateTime.now());
		InternadoEntity savedInternado = internadoRepository.save(internado);
		
		Triagem triagem = internado.getTriagem();
		triagemService.setFinish(Boolean.TRUE, triagem.getId());
		
		return savedInternado;
	}

}
