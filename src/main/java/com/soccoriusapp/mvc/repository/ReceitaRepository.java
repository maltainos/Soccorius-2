package com.soccoriusapp.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soccoriusapp.mvc.entity.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Integer> {

}
