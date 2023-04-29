package com.soccoriusapp.mvc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.soccoriusapp.mvc.entity.Paciente;
import com.soccoriusapp.mvc.model.rest.ReportIncomeWeklyRest;

@Repository
public interface PacienteRepository extends PagingAndSortingRepository<Paciente, Integer> {

	@Query("SELECT p FROM Paciente p WHERE CONCAT(p.id, ' ',p.pacienteCode, ' ',p.email, ' ',p.firstName, ' ',p.lastName, ' ',p.fathersName, ' ',p.mothersName) LIKE %?1%")
	Page<Paciente> findAll(String keyword, Pageable pageable);
	
	@Query("SELECT new com.soccoriusapp.mvc.model.rest.ReportIncomeWeklyRest("
			+ "DAYNAME(p.createdOn), count(p.id)) \n"
			+ "	FROM Paciente p WHERE p.createdOn \n"
			+ "	BETWEEN ?2 AND ?1 GROUP BY DAYNAME(p.createdOn)")
	List<ReportIncomeWeklyRest> reportPerWeek(LocalDate today, LocalDate stop);
}
