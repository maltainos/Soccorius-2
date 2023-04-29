package com.soccoriusapp.mvc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.soccoriusapp.mvc.entity.LaboratorioEntity;

@Repository
public interface LaboratorioRepository extends PagingAndSortingRepository<LaboratorioEntity, Integer> {

	@Query("SELECT l FROM LaboratorioEntity l WHERE concat(l.serieAmostra, '', l.tipoExame, '', l.horaEntrada, '', l.paciente.pacienteCode) LIKE %?1%")
	Page<LaboratorioEntity> findAll(String keyword, Pageable pageable);

}
