package com.soccoriusapp.mvc.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soccoriusapp.mvc.entity.LaboratorioEntity;
import com.soccoriusapp.mvc.service.impl.LaboratorioServiceImpl;

@Controller
@RequestMapping(path = "/laboratorios")
public class LaboratorioController {
	
	@Autowired
	private LaboratorioServiceImpl laboratorioService;

	@GetMapping
	public String showConsultasFirstPage(Model model) {
		
		return showConsultasPerPage(1,10,"asc","id","",model);
	}
	
	@GetMapping(path = "/page/{page}")
	public String showConsultasPerPage(@PathVariable(name = "page") int page, int limit,
			String sortMode, String sortColumn, String keyword, Model model) {
		
		Page<LaboratorioEntity> laboratorios = laboratorioService.searchLaboratorios(page, limit, sortColumn, sortMode, keyword);
		String reverseSortMode = sortColumn.equalsIgnoreCase("asc") ? "desc" : "asc";
		
		model.addAttribute("keyword", keyword);
		model.addAttribute("page", page);
		model.addAttribute("limit", limit);
		model.addAttribute("reverseSortMode", reverseSortMode);
		model.addAttribute("laboratorios", laboratorios.getContent());
		return "laboratorios/laboratorios";
	}
	
	
	@PostMapping(path = "save")
	public String saveLaboratorioRequest(LaboratorioEntity laboratorio, Model model, RedirectAttributes attributes) {
		
		
		LaboratorioEntity savedLaboratorio = laboratorioService.createLaboratorio(laboratorio);
		System.out.println(savedLaboratorio.getId());
		return "redirect:/laboratorios";
	}
}
