package com.soccoriusapp.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soccoriusapp.mvc.entity.UserEntity;
import com.soccoriusapp.mvc.service.impl.UserServiceImpl;

@Controller
@RequestMapping(path = "/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;

	@GetMapping
	public String showUsersPage(Model model) {
		
		List<UserEntity> users = userService.listUsers(1, 10, "id", "asc");
		
		model.addAttribute("pageTitle", "Usuarios");
		model.addAttribute("users", users);
		return "users/users";
	}
	
	@GetMapping(path = "/new")
	public String showUsersNewFormPage(Model model) {
		
		UserEntity user = new UserEntity();
		
		model.addAttribute("user", user);
		model.addAttribute("pageTitle", "Adicionar Usuario");
		return "users/users_form";
	}
	
	@PostMapping(path = "/save")
	public String createNewUser(UserEntity user, RedirectAttributes redirectAttributes) {
		
		UserEntity createdUser = userService.createUser(user);
	
		redirectAttributes.addFlashAttribute("message", "Utilizador criado com sucesso!");
		return "redirect:/users";
	}
	
	@GetMapping(path = "/delete/{userId}")
	public String deleteUser(@PathVariable Integer userId, RedirectAttributes redirectAttributes) {
		 
		redirectAttributes.addFlashAttribute("message", "Utilizador removido com sucesso!");
		return "redirect:/users";
	}
}
