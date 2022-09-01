package com.soccoriusapp.mvc.repository.role;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.soccoriusapp.mvc.entity.RoleEntity;
import com.soccoriusapp.mvc.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Test
	public void testCreateRole() {
		
		RoleEntity role = new RoleEntity("Medical", "This Manager Everthing about patients, clinical history, laboratory results, income and outcome");
		
		RoleEntity savedRole = roleRepository.save(role);
		
		assertThat(savedRole).isNotNull();
		assertThat(savedRole.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateOutherRoles() {
		
		RoleEntity assitente = new RoleEntity("Assistente", "This See Everthing about patients, clinical history, income and outcome");
		RoleEntity enfermeiro = new RoleEntity("Enfermeiro", "This Manager Some Data about patients, clinical history, income and outcome");
		RoleEntity recepcionista = new RoleEntity("Recepcionista", "This See Everthing about patients, income and outcome");
		
		
		List<RoleEntity> savedRoles = roleRepository.saveAll(List.of(assitente, enfermeiro, recepcionista));
		
		assertThat(savedRoles).isNotNull();
		assertThat(savedRoles.size()).isGreaterThan(0);
	}

}
