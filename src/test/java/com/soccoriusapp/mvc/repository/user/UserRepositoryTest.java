package com.soccoriusapp.mvc.repository.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.soccoriusapp.mvc.entity.RoleEntity;
import com.soccoriusapp.mvc.entity.UserEntity;
import com.soccoriusapp.mvc.repository.RoleRepository;
import com.soccoriusapp.mvc.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void testAddUserRole() {
		Optional<RoleEntity> medicalRole = roleRepository.findById(5);
		
		Optional<UserEntity> foundedUser1 = userRepository.findById(3);
		Optional<UserEntity> foundedUser2 = userRepository.findById(4);
		UserEntity userMedical1 = foundedUser1.get();
		UserEntity userMedical2 = foundedUser2.get();
		userMedical1.addRole(medicalRole.get());
		userMedical1 = userRepository.save(userMedical1);
		
		userMedical2.addRole(medicalRole.get());
		userMedical2 = userRepository.save(userMedical1);
	
		assertThat(userMedical1.getRoles().size()).isGreaterThan(0);
		assertThat(userMedical2.getRoles().size()).isGreaterThan(0);
	}
	
	@Test
	public void testAddUserRoles() {
		Optional<RoleEntity> assistentRole = roleRepository.findById(2);
		Optional<RoleEntity> receptionRole = roleRepository.findById(4);
		
		Optional<UserEntity> foundedUser = userRepository.findById(14);
		UserEntity userAdmin = foundedUser.get();
		userAdmin.addRole(assistentRole.get());
		userAdmin.addRole(receptionRole.get());
		userAdmin = userRepository.save(userAdmin);
	
		assertThat(userAdmin.getRoles().size()).isGreaterThan(0);
	}

}
