package com.soccoriusapp.mvc.service;

import org.springframework.data.domain.Page;

import com.soccoriusapp.mvc.entity.Triagem;

public interface TriagemService {

	public Triagem saveTriagem(Triagem triagem);

	public Page<Triagem> getTriagemPerPage(int page, int limit, String sortColumn, String sortMode, String keyword);

	public Integer getLastTriagemToday();

	public Triagem getTriagem(Integer id);

	public void setFinish(Boolean status, Integer triagemId);

	Long countTriagens();

}
