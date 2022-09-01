package com.soccoriusapp.mvc.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.soccoriusapp.mvc.entity.UserEntity;
import com.soccoriusapp.mvc.repository.UserRepository;

public class SoccoriusUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<UserEntity> loadedUser = userRepository.findByEmail(username);
		
		if(loadedUser.isPresent()) {
			return new SoccoriusUserDetails(loadedUser.get());
		}
		
		throw new UsernameNotFoundException("Usuario não encontrado com Username: "+username);
	}

}
