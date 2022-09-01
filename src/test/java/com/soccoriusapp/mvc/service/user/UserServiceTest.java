package com.soccoriusapp.mvc.service.user;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.soccoriusapp.mvc.entity.UserEntity;
import com.soccoriusapp.mvc.repository.UserRepository;
import com.soccoriusapp.mvc.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@Rollback(false)
public class UserServiceTest {
	
	@MockBean
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Test
	public void testPasswordEncoder() {
		Integer id = 1;
		String password = "soccorius@2022";
		
		Optional<UserEntity> user = Optional.ofNullable(new UserEntity(id, password));
		Mockito.when(userRepository.findById(id)).thenReturn(user);
		
		
		UserEntity updateUser = userRepository.findById(id).get();
		updateUser.setEncryptPassword(password);
		updateUser = userService.createUser(user.get());
		
		System.out.println(updateUser);
		//assertThat(updateUser).isNotNull();
	}

}
