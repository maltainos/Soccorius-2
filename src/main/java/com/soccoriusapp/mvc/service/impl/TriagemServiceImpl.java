package com.soccoriusapp.mvc.service.impl;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.soccoriusapp.mvc.entity.Receita;
import com.soccoriusapp.mvc.entity.Triagem;
import com.soccoriusapp.mvc.model.rest.TriagemRest;
import com.soccoriusapp.mvc.repository.ReceitaRepository;
import com.soccoriusapp.mvc.repository.TriagemRepository;
import com.soccoriusapp.mvc.service.TriagemService;
import com.soccoriusapp.mvc.service.exception.TriagemNotFoundException;

@Service
public class TriagemServiceImpl implements TriagemService {
	
	public static final int LIMIT = 10;
	
	@Autowired
	private TriagemRepository triagemRepository;
	
	@Autowired
	private ReceitaRepository receitaRepository;
	
	@Override
	public Triagem saveTriagem(Triagem triagem) {
		
		setAdditionalInformation(triagem);
		return triagemRepository.save(triagem);
	}

	private void setAdditionalInformation(Triagem triagem) {
		triagem.setCreateAt(LocalDate.now());
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
		return triagemRepository.countLastTriagemToday(LocalDate.now().minusDays(1), LocalDate.now());
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
	public Page<Triagem> findReport(int page, String doenca, String bairro, LocalDate dateStart, LocalDate dateEnd) {
		
		//Pageable pageable = PageRequest.of(page, LIMIT);
	
		//Page<Triagem> returnValue = triagemRepository.findReport(pageable, doenca, bairro, dateStart, dateEnd);
		
		return null;// returnValue;
		
	}

	@Override
	public Triagem setReceita(Receita receita, String doenca) {
		Receita savedReceita =  receitaRepository.save(receita);
		
		Triagem returnValue = savedReceita.getTriagem();
		
		setTriagemDoenca(returnValue, doenca);
		return returnValue;
	}
	
	public void setTriagemDoenca(Triagem triagem, String doenca) {
		
		triagem.setDoenca(doenca);
		triagemRepository.save(triagem);
	}

	@Override
	public List<TriagemRest> getTriagemReport(LocalDate startAt, LocalDate endAt) {
		
		List<TriagemRest> returnValue = new ArrayList<TriagemRest>();
		
		for(int mes = 1; mes <= 12; mes++) {
			int mesTamanhoMaximo = Month.of(mes).maxLength();
			if(mes == 2) {
				if(startAt.getYear() % 4 != 0) {
					mesTamanhoMaximo = 28;
				}
			}
			for(int dia = 1; dia <= mesTamanhoMaximo; dia++) {
				returnValue.add(new TriagemRest(LocalDate.of(startAt.getYear(), mes, dia), new Random().nextLong(100)));
			}
		}
		List<TriagemRest> triagemReport = triagemRepository.getYearlyReport(startAt, endAt);
		
		returnValue.forEach(singleValue -> {
			triagemReport.forEach(report -> {
				if(singleValue.getData().isEqual(report.getData())){
					singleValue.setTotalData(report.getTotalData());
				}
				//singleValue.setTotalData(singleValue.getData().isEqual(report.getData()) ? report.getTotalData(): 0l); 
			});
		});
		return returnValue;
	}

}
