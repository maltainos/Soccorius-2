package com.soccoriusapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soccoriusapp.mvc.service.impl.EncaminhaServiceImpl;
import com.soccoriusapp.mvc.service.impl.InternadoServiceImpl;
import com.soccoriusapp.mvc.service.impl.LaboratorioServiceImpl;
import com.soccoriusapp.mvc.service.impl.ObitoServiceImpl;
import com.soccoriusapp.mvc.service.impl.PacienteServiceImpl;
import com.soccoriusapp.mvc.service.impl.TransferenciaServiceImpl;
import com.soccoriusapp.mvc.service.impl.TriagemServiceImpl;
import com.soccoriusapp.mvc.service.impl.UserServiceImpl;

@Controller
public class SoccoriusController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private ObitoServiceImpl obitoService;
	
	@Autowired
	private TriagemServiceImpl triagemService;
	
	@Autowired
	private PacienteServiceImpl pacienteService;
	
	@Autowired
	private InternadoServiceImpl internadoService;
	
	@Autowired
	private EncaminhaServiceImpl encaminhadoService;

	@Autowired
	private LaboratorioServiceImpl laboratorioService;
	
	@Autowired
	private TransferenciaServiceImpl transferenciaService;
	
	@GetMapping
	public String showHomePage(Model model) {
	
		
		model.addAttribute("pageTitle", "Dashboard");
		
		model.addAttribute("totalOfUsers", userService.countUser());
	
		model.addAttribute("totalOfObitos", obitoService.countObitos());
		model.addAttribute("totalOfPatients", pacienteService.countPacientes());
		model.addAttribute("totalOfConsultas", triagemService.countTriagens());
		model.addAttribute("totalOfInternados", internadoService.countInternados());
		model.addAttribute("totalOfEncaminhados", encaminhadoService.countEncaminhados());
		model.addAttribute("totalOfLaboratorios", laboratorioService.countLaboratorios());
		model.addAttribute("totalOfTransferencias", transferenciaService.countTransferencias());
		
		return "index";
	}
	
	@GetMapping(path = "/vendors/bootstrap/fonts/glyphicons-halflings-regular.ttf")
	public String redirectDefault(Model model, @RequestParam(name = "v", defaultValue = "") String v) {
		return "redirect:/";
	}
	@GetMapping(path = "/vendors/font-awesome/fonts/fontawesome-webfont.ttf")
	public String redirectParam(Model model, @RequestParam(name = "v", defaultValue = "") String v) {
		return "redirect:/";
	}
	
}
