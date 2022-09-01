package com.soccoriusapp.mvc.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soccoriusapp.mvc.entity.Paciente;
import com.soccoriusapp.mvc.entity.Triagem;
import com.soccoriusapp.mvc.entity.UserEntity;
import com.soccoriusapp.mvc.security.SoccoriusUserDetails;
import com.soccoriusapp.mvc.service.impl.PacienteServiceImpl;
import com.soccoriusapp.mvc.service.impl.TriagemServiceImpl;

@Controller
@RequestMapping(path = "/triagem")
public class TriagemController {
	
	@Autowired
	private PacienteServiceImpl pacienteService;
	
	@Autowired
	private TriagemServiceImpl triagemService;
	
	@GetMapping
	public String showConsultasFirstPage(Model model) {
		
		return showTriagemPerPage(model, 1, 10, "id", "asc", "");
	}
	
	public String showTriagemPerPage(Model model, int page, int limit, String sortColumn, String sortMode, String keyword) {
		Page<Triagem> triagensPage = triagemService.getTriagemPerPage(page, limit, sortColumn, sortMode, keyword);
		
		model.addAttribute("triagens", triagensPage.getContent());
		return "triagem/triagem";
	}

	@GetMapping(path = "/new")
	public String showNewTriagemPage(@AuthenticationPrincipal SoccoriusUserDetails user ,Model model) {
		Integer lastToday = triagemService.getLastTriagemToday();

		Triagem triagem = new Triagem();
		List<Paciente> pacientes = pacienteService.getPacientes();
		triagem.setTriagemNumber(lastToday + 1);
		
		model.addAttribute("pageTitle", "Nova Traigem");
		model.addAttribute("pacientes", pacientes);
		model.addAttribute("assistente", user.getUsername());
		model.addAttribute("triagem", triagem);
		return "triagem/triagem_form";
	}
	
	@PostMapping(path = "/save")
	public String saveNewTriagem(@AuthenticationPrincipal SoccoriusUserDetails principal, Model model, Triagem triagem, RedirectAttributes attributes) {
		
		triagem.setAssistente(new UserEntity(principal.getId(), principal.getPassword()));
		Triagem savedTriagem = triagemService.saveTriagem(triagem);
		attributes.addFlashAttribute("message", "Marcação adicionada com successo!");
		return "redirect:/triagem?keyword="+savedTriagem.getTriagemNumber();
	}
	
	@GetMapping(path = "/{id}/details")
	public String showNewTriagemDetailsPage(@PathVariable Integer id, Model model) {
		Integer lastToday = triagemService.getLastTriagemToday();

		Triagem triagem = triagemService.getTriagem(id);
		//List<Paciente> pacientes = pacienteService.getPacientes();
		triagem.setTriagemNumber(lastToday + 1);
		
		model.addAttribute("pageTitle", "Details Traigem");
		//model.addAttribute("pacientes", pacientes);
		model.addAttribute("assistente", triagem.getAssistente());
		model.addAttribute("triagem", triagem);
		return "triagem/triagem_details";
	}
	
	@GetMapping(path = "/details/{id}/laboratorio")
	public String showTriagemLaboratorioResult(@AuthenticationPrincipal SoccoriusUserDetails authUser, @PathVariable Integer id, Model model) {

		Triagem triagem = triagemService.getTriagem(id);
		
		model.addAttribute("pageTitle", "Resultados de Laboratorio");
		
		model.addAttribute("laboratorista", authUser);
		model.addAttribute("triagem", triagem);
		return "triagem/triagem_laboratorio";
	}

}
