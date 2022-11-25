package com.soccoriusapp.mvc.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.soccoriusapp.mvc.entity.Triagem;

@Repository
public interface TriagemRepository extends PagingAndSortingRepository<Triagem, Integer> {

	@Query("SELECT count(t.id) FROM Triagem t WHERE createAt BETWEEN ?1 AND ?2")
	public Integer countLastTriagemToday(LocalDateTime yestaday, LocalDateTime today);

	@Query("SELECT t FROM Triagem t WHERE t.triagemStatus = ?1")
	public Page<Triagem> findAll(Pageable pageable, boolean status);

	@Query("UPDATE Triagem t SET t.triagemStatus = ?1 WHERE t.id = ?2")
	@Modifying
	public void setFinish( boolean status, Integer triagemId);
	
	
	//@Query("SELECT t FROM Triagenm t INNER JOIN Paciente p WHERE t.sintomas LIKE '%1?%' and t.create_at between 3?' AND '4?' AND p.address LIKE '%2?%' AND p.id = t.paciente_id")
	//public Triagem report(String doenca, String bairo, String start, String endp);
}
