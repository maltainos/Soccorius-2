package com.soccoriusapp.mvc.controller.wsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccoriusapp.mvc.service.impl.UserServiceImpl;

@RestController
@RequestMapping(path = "/wsapi/users")
public class UserRestController {

	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping
	public boolean checkUserUnique(@Param("id") Integer id, @Param("email") String email) {
		
		return userService.checkUserUnique(id, email);
	}
}
