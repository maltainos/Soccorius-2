package com.soccoriusapp.mvc.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soccoriusapp.mvc.entity.UserEntity;
import com.soccoriusapp.mvc.service.exception.UserNotFoundException;
import com.soccoriusapp.mvc.service.impl.UserServiceImpl;

@Controller
@RequestMapping(path = "/users")
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping
	public String showFirstUsersPage(Model model) {
		return showUsersPage(model, 1, 10, "id", "asc", "");
	}

	@GetMapping(path = "/page/{page}")
	public String showUsersPage(Model model, @PathVariable int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit, 
			@RequestParam(name = "sortColumn", defaultValue = "id") String sortColumn,
			@RequestParam(name = "sortMode", defaultValue = "asc") String sortMode,
			@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		
		Page<UserEntity> users = userService.listUsers(page, limit, sortColumn, sortMode , keyword);
		
		model.addAttribute("pageTitle", "Usuarios");
		model.addAttribute("users", users.getContent());
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("sortColumn", sortColumn);
		model.addAttribute("sortMode", sortMode);
		/*Pagination Information*/
		
		model.addAttribute("currentPage", page);
		model.addAttribute("limit", limit);
		
		model.addAttribute("totalOfPages", users.getTotalPages());
		model.addAttribute("totalOfElements", users.getNumberOfElements());
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
	public String createNewUser(UserEntity user, RedirectAttributes redirectAttributes) throws UserNotFoundException {
		
		if(user.getId() == null) {
			userService.createUser(user);
			redirectAttributes.addFlashAttribute("message", "Utilizador criado com sucesso!");
		}else {
			userService.updateUser(user.getId(), user);
			redirectAttributes.addFlashAttribute("message", "Utilizador actualizado com sucesso!");
		}
		return getRedirectUsersUrl(user);
	}

	private String getRedirectUsersUrl(UserEntity user) {
		String firstPartOfEmail = user.getEmail().split("@")[0];
		return "redirect:/users/page/1?sortColumn=id&sortMode=asc&keyword="+firstPartOfEmail;
	}
	
	@GetMapping(path = "/edit/{userId}")
	public String showEditUserPage(@PathVariable Integer userId, Model model) throws UserNotFoundException {
		
		UserEntity user = userService.findUser(userId);
		
		model.addAttribute("user", user);
		model.addAttribute("pageTitle", "Ediatr Usuario");
		return "users/users_form";
	}
	
	@GetMapping(path = "/delete/{userId}")
	public String deleteUser(@PathVariable Integer userId, RedirectAttributes redirectAttributes) {
		 
		redirectAttributes.addFlashAttribute("message", "Utilizador removido com sucesso!");
		return "redirect:/users";
	}
}
