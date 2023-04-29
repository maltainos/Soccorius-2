package com.soccoriusapp.mvc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.soccoriusapp.mvc.entity.Triagem;

@Repository
public interface ReportRepository extends PagingAndSortingRepository<Triagem, Integer>{
	
	//@Query("SELECT t, p FROM Triagem t INNER JOIN Paciente p WHERE CONCAT() LIKE %?2%  AND t.paciente = p.id")
	//public Page<ConsultaEntity> getReporty(Pageable pageable, String bairro, String doenca);

}
