package com.soccoriusapp.mvc.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.soccoriusapp.mvc.entity.InternadoEntity;

@Repository
public interface InternadoRepository extends PagingAndSortingRepository<InternadoEntity, Integer> {

}
