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

import com.soccoriusapp.mvc.entity.Paciente;
import com.soccoriusapp.mvc.service.exception.PacienteNotFoundException;
import com.soccoriusapp.mvc.service.impl.PacienteServiceImpl;

@Controller
@RequestMapping(path = "/pacientes")
public class PacienteController {

	@Autowired
	private PacienteServiceImpl pacienteService;
	
	@GetMapping
	public String showFirstPageOfPacientes(Model model) {
		
		return showPacientePerPage(model, 1, 10, "id", "asc", "");
	}
	
	@GetMapping(path = "/page/{page}")
	public String showPacientePerPage(Model model, @PathVariable(name = "page") int page, 
			@RequestParam(name = "limit", defaultValue = "10") int limit, 
			@RequestParam(name = "sortColumn", defaultValue = "id") String sortColumn, 
			@RequestParam(name = "sortMode", defaultValue = "asc") String sortMode, 
			@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		
		Page<Paciente> pacientes = pacienteService.searchPacientes(page, limit, sortColumn, sortMode, keyword);
		
		model.addAttribute("pageTitle", "Pacientes");
	
		// INFOMRATION FOR SORT AND SEARCH
		model.addAttribute("sortColumn", sortColumn);
		model.addAttribute("sortMode", sortMode);
		model.addAttribute("keyword", keyword);
		
		//INFORMATION FOR PAGINATION
		model.addAttribute("limit", limit);
		model.addAttribute("currentPage", page);
		model.addAttribute("totalOfElements", pacientes.getTotalElements());
		model.addAttribute("totalOfPages", pacientes.getTotalPages());
		
		//CONTENT FOR THIS PAGE
		model.addAttribute("listOfPacientes", pacientes.getContent());
		
		return "pacientes/pacientes";
	}
	
	@GetMapping(path = "/new")
	public String showPacienteForm(Model model) {
		Paciente paciente = new Paciente();
		
		model.addAttribute("pageTitle", "Paciente | Novo Paciente");
		model.addAttribute("paciente", paciente);
		return "pacientes/pacientes_form";
	}
	
	@PostMapping(path = "/save")
	public String savePaciente(Model mode, Paciente paciente, RedirectAttributes attributes) {
		
		Paciente newPaciente = pacienteService.createPaciente(paciente);
		attributes.addFlashAttribute("message", "Paciente salvo com sucesso!");
		return "redirect:/pacientes/page/1?keyword="+newPaciente.getPacienteCode();
	}
	
	@GetMapping(path = "/edit/{id}")
	public String showPacienteForm(Model model, @PathVariable(name = "id") Integer id, 
			RedirectAttributes attributes) {
		
		model.addAttribute("pageTitle", "Paciente | Editar Paciente");
		
		try {
			Paciente paciente = pacienteService.getPaciente(id);
			model.addAttribute("paciente", paciente);
		}catch (PacienteNotFoundException e) {
			attributes.addFlashAttribute("message", "Paciente não encontrado!");
			return "redirec:/pacientes";
		}
		
		return "pacientes/pacientes_form";
	}
}
