package com.soccoriusapp.mvc.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.soccoriusapp.mvc.entity.UserEntity;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer>{

	@Query("SELECT u FROM UserEntity u WHERE CONCAT(u.id, ' ', u.firstName, ' ', u.lastName, ' ', u.email, ' ') LIKE %?1%")
	public Page<UserEntity> findAll(String keyword, Pageable pageable);
	
	public Optional<UserEntity> findByEmail(String email);
}
