package com.soccoriusapp.mvc.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.soccoriusapp.mvc.entity.UserEntity;
import com.soccoriusapp.mvc.repository.UserRepository;
import com.soccoriusapp.mvc.service.UserService;
import com.soccoriusapp.mvc.service.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Page<UserEntity> listUsers(int page, int limit, String sortColumn, String sortMode, String keyword) {
		
		if(page > 0) page -= 1;
		
		Sort sort = sortMode.equalsIgnoreCase("asc") ? Sort.by(sortColumn).ascending() 
				: Sort.by(sortColumn).descending();
		
		
		Pageable pageable = PageRequest.of(page, limit, sort);
		
		Page<UserEntity> pageOfUsers =  userRepository.findAll(keyword, pageable);
		
		return pageOfUsers;
	}

	@Override
	public UserEntity createUser(UserEntity user) {
		
		UserEntity newUser = user;
		
		setPasswordEncoder(user);
		newUser.setCreatedOn(LocalDate.now());
		
		return userRepository.save(newUser);
	}
	
	private void setPasswordEncoder(UserEntity userEntity) {
		userEntity.setEncryptPassword(userEntity.getEncryptPassword());
	}

	@Override
	public Long countUser() {
		return userRepository.count();
	}

	@Override
	public UserEntity findUser(Integer userId) throws UserNotFoundException {
		
		Optional<UserEntity> userFounded = userRepository.findById(userId);
		
		if(!userFounded.isPresent())
			throw new UserNotFoundException("Usuario com ID: "+userId+" Nao encontrado!");
		return userFounded.get();
	}

	@Override
	public void deleteUser(Integer userId) throws UserNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserEntity updateUser(Integer userId, UserEntity user) throws UserNotFoundException {
		
		UserEntity userFounded = findUser(userId);
		
		user.setId(userFounded.getId());
		//user.setEmail(userFounded.getEmail());
		
		user.setCreatedOn(userFounded.getCreatedOn());
		user.setUpdatedOn(LocalDate.now());
		return userRepository.save(user);
	}

	public boolean checkUserUnique(Integer id, String email) {
		
		Optional<UserEntity> checkedUser = userRepository.findByEmail(email);
		
		if(checkedUser.isPresent() &&  (id == null || id == 0))//CREATE MODE
			return false;
		
		if(checkedUser.isPresent()) {
			if(checkedUser.get().getId() != id && !(id == null || id == 0))//EDIT MODE
				return false;
		}
		
		return true;
	}

	public UserEntity getUserByEmail(String email) throws UserNotFoundException {
		
		Optional<UserEntity> checkedUser = userRepository.findByEmail(email);
		
		if(!checkedUser.isPresent()) throw new UserNotFoundException("Usuario nao encontrado!");
		
		return null;
	}

	@Override
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}
	

}
