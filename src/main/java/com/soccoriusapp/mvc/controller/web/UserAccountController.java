package com.soccoriusapp.mvc.controller.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soccoriusapp.mvc.security.SoccoriusUserDetails;

@Controller
@RequestMapping(path = "/account")
public class UserAccountController {

	@GetMapping
	public String showAccountInformation(@AuthenticationPrincipal SoccoriusUserDetails authUser, Model model,
			RedirectAttributes attributes) {

		model.addAttribute("pageTitle", "Minha Conta");
		model.addAttribute("user", authUser.getUser());

		return "users/account";
	}
}
