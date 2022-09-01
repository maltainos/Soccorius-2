package com.soccoriusapp.mvc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.soccoriusapp.mvc.entity.Paciente;

@Repository
public interface PacienteRepository extends PagingAndSortingRepository<Paciente, Integer> {

	@Query("SELECT p FROM Paciente p WHERE CONCAT(p.id, ' ',p.pacienteCode, ' ',p.email, ' ',p.firstName, ' ',p.lastName, ' ',p.fathersName, ' ',p.mothersName) LIKE %?1%")
	Page<Paciente> findAll(String keyword, Pageable pageable);
}
