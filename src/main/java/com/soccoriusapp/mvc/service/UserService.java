package com.soccoriusapp.mvc.service;

import java.util.List;

import com.soccoriusapp.mvc.entity.UserEntity;

public interface UserService {
	
	List<UserEntity> listUsers(int page, int limit, String sortColumn, String sortMode);
	UserEntity createUser(UserEntity user);

}
