package com.soccoriusapp.mvc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.soccoriusapp.mvc.entity.LaboratorioEntity;

@Repository
public interface LaboratorioRepository extends PagingAndSortingRepository<LaboratorioEntity, Integer> {

}
