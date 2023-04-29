package com.soccoriusapp.mvc.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;

import com.soccoriusapp.mvc.entity.Receita;
import com.soccoriusapp.mvc.entity.Triagem;
import com.soccoriusapp.mvc.model.rest.TriagemRest;

public interface TriagemService {

	public Triagem saveTriagem(Triagem triagem);

	public Page<Triagem> getTriagemPerPage(int page, int limit, String sortColumn, String sortMode, String keyword);

	public Integer getLastTriagemToday();

	public Triagem getTriagem(Integer id);

	public void setFinish(Boolean status, Integer triagemId);

	Long countTriagens();

	Page<Triagem> findReport(int page, String doenca, String bairo, LocalDate dateStart, LocalDate dateEnd);

	Triagem setReceita(Receita receita, String doenca);
	
	List<TriagemRest> getTriagemReport(LocalDate startAt, LocalDate endAt);

}
