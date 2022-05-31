package com.soccoriusapp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soccoriusapp.mvc.entity.UserEntity;

@Controller
@RequestMapping(path = "/users")
public class UserController {

	@GetMapping
	public String showUsersPage(Model model) {
		
		model.addAttribute("pageTitle", "Usuarios");
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
		System.out.println(user.getEmail());
		return "redirect:/users";
	}
}
