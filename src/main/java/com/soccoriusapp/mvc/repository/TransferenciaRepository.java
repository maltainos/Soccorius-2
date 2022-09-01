package com.soccoriusapp.mvc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.soccoriusapp.mvc.entity.TransferenciaEntity;

@Repository
public interface TransferenciaRepository extends PagingAndSortingRepository<TransferenciaEntity, Integer>{

}
