package com.soccoriusapp.mvc.repository;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.soccoriusapp.mvc.entity.Receita;
import com.soccoriusapp.mvc.model.rest.TriagemReceitaPacienteRest;

@Repository
public interface TriagemReceitaPacienteRepository extends JpaRepository<Receita, Integer> {

	@Query("SELECT new com.soccoriusapp.mvc.model.rest.TriagemReceitaPacienteRest"
			+ "(r.medicamentos, p.pacienteCode, p.firstName, p.lastName, p.dateOfBirth, "
			+ "p.address, t.triagemNumber, t.sintomas, t.doenca, t.createAt) FROM Triagem t, "
			+ "Receita r, Paciente p WHERE (t.doenca LIKE %?1%) AND (p.address LIKE %?2%) AND (t.createAt BETWEEN ?3 AND ?4)")
	public Page<TriagemReceitaPacienteRest> getTriagemReceitaReport(
			String doenca, String bairro, LocalDate dateStart, LocalDate dateEnd,Pageable pageable);	

}
