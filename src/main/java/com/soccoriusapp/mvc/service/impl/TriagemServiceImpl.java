package com.soccoriusapp.mvc.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.soccoriusapp.mvc.entity.Triagem;
import com.soccoriusapp.mvc.repository.TriagemRepository;
import com.soccoriusapp.mvc.service.TriagemService;
import com.soccoriusapp.mvc.service.exception.TriagemNotFoundException;

@Service
public class TriagemServiceImpl implements TriagemService {
	
	@Autowired
	private TriagemRepository triagemRepository;
	
	@Override
	public Triagem saveTriagem(Triagem triagem) {
		
		setAdditionalInformation(triagem);
		return triagemRepository.save(triagem);
	}

	private void setAdditionalInformation(Triagem triagem) {
		triagem.setCreateAt(LocalDateTime.now());
	}

	@Override
	public Page<Triagem> getTriagemPerPage(int page, int limit, String sortColumn, String sortMode, String keyword) {
		
		if(page > 0) page -= page;
		
		Sort sort = sortMode.equalsIgnoreCase("asc") ? Sort.by(sortColumn).ascending() 
				: Sort.by(sortColumn).descending();
		
		Pageable pageable = PageRequest.of(page, limit, sort);
		Page<Triagem> triagemPage = triagemRepository.findAll(pageable, false);
		
		return triagemPage;
	}
	
	@Override
	public Integer getLastTriagemToday() {
		return triagemRepository.countLastTriagemToday(LocalDateTime.now().minusDays(1), LocalDateTime.now());
	}
	
	@Override
	public Triagem getTriagem(Integer id) {
		
		Optional<Triagem> triagem = triagemRepository.findById(id);
		if(!triagem.isPresent()) throw new TriagemNotFoundException("Triagem não encontrda!");
		return triagem.get();
	}

	@Override
	@Transactional
	public void setFinish(Boolean status, Integer triagemId) {
		triagemRepository.setFinish(status, triagemId);
	}

	@Override
	public Long countTriagens() {
		return triagemRepository.count();
	}

	@Override
	public Triagem findReport(String doenca, String bairo, String dateStart, String dateEnd) {
		return new Triagem();
		
	}

}
