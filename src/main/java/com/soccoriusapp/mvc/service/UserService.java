package com.soccoriusapp.mvc.service;

import org.springframework.data.domain.Page;

import com.soccoriusapp.mvc.entity.UserEntity;
import com.soccoriusapp.mvc.service.exception.UserNotFoundException;

public interface UserService {
	
	Page<UserEntity> listUsers(int page, int limit, String sortColumn, String sortMode, String keyword);
	UserEntity createUser(UserEntity user);
	void deleteUser(Integer userId) throws UserNotFoundException;
	UserEntity updateUser(Integer userId, UserEntity user) throws UserNotFoundException;
	Long countUser();
	UserEntity findUser(Integer userId) throws UserNotFoundException;

}
