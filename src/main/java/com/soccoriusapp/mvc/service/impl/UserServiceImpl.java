package com.soccoriusapp.mvc.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.soccoriusapp.mvc.entity.UserEntity;
import com.soccoriusapp.mvc.repository.UserRepository;
import com.soccoriusapp.mvc.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<UserEntity> listUsers(int page, int limit, String sortColumn, String sortMode) {
		
		if(page > 0) page -= 1;
		
		Sort sort = sortMode.equalsIgnoreCase("asc") ? Sort.by(sortColumn).ascending() 
				: Sort.by(sortColumn).descending();
		
		
		Pageable pageable = PageRequest.of(page, limit, sort);
		
		Page<UserEntity> pageOfUsers =  userRepository.findAll(pageable);
		
		return pageOfUsers.getContent();
	}

	@Override
	public UserEntity createUser(UserEntity user) {
		
		UserEntity newUser = user;
		
		newUser.setCreatedOn(LocalDate.now());
		
		return userRepository.save(newUser);
	}
	

}
