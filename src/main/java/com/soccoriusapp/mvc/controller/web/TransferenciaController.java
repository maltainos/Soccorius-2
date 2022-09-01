package com.soccoriusapp.mvc.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.soccoriusapp.mvc.entity.TransferenciaEntity;
import com.soccoriusapp.mvc.entity.Triagem;
import com.soccoriusapp.mvc.security.SoccoriusUserDetails;
import com.soccoriusapp.mvc.service.impl.TransferenciaServiceImpl;
import com.soccoriusapp.mvc.service.impl.TriagemServiceImpl;

@Controller
public class TransferenciaController {

	@Autowired
	private TriagemServiceImpl triagemService;
	
	@Autowired
	private TransferenciaServiceImpl transferenciaService;
	
	@RequestMapping(value = "/transferencias", method = RequestMethod.GET)
	public String showTransferenciaFirstPage(Model model) {
		return showTransferenciaPerPage(1, 10,"id","asc","", model);
	}
	
	@RequestMapping(value = "/transferencias/page/{page}", method = RequestMethod.GET)
	public String showTransferenciaPerPage(@PathVariable int page, @RequestParam(name = "limit", 
				defaultValue = "10") int limit, @RequestParam(name = "sortColumn", 
				defaultValue = "id") String sortColumn, @RequestParam(name = "sortMode", 
				defaultValue = "asc") String sortMode, @RequestParam(name = "keyword", 
				defaultValue = "keyword") String keyword, Model model) {
		
		Page<TransferenciaEntity> transferencias = transferenciaService.getTransferencias(page, limit, sortColumn, sortMode);
		
		model.addAttribute("currentPage", page);
		model.addAttribute("limit", limit);
		model.addAttribute("keyword", keyword);
		model.addAttribute("sortColumn", sortColumn);
		model.addAttribute("sortMode", sortMode);
		String reverseSortMode = sortMode.equalsIgnoreCase("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortMode", reverseSortMode);
		model.addAttribute("transferencias", transferencias.getContent());
		return "transferencias/transferencias";
	}
	
	@RequestMapping(value = "/triagem/paciente/{id}/transferir", method = RequestMethod.GET)
	public String showTransferirPage(@AuthenticationPrincipal SoccoriusUserDetails authUser, 
			@PathVariable Integer id,Model model) {
		
		Triagem triagem = triagemService.getTriagem(id);
		TransferenciaEntity transferencia = new TransferenciaEntity();
		transferencia.setTriagem(triagem);
		transferencia.setPaciente(triagem.getPaciente());
		transferencia.setMedicoResponsavel(authUser.getUser());
		
		model.addAttribute("pageTitle", "Transferir Paciente");
		model.addAttribute("transferencia", transferencia);
		
		return "transferencias/transferir";
	}
	
	@RequestMapping(value = "/triagem/{triagemId}/transferencia/{transferenciaId}", method = RequestMethod.GET)
	public String showTransferenciaPage(@PathVariable Integer triagemId, @PathVariable 
			Integer transferenciaId, Model model) {
		
		TransferenciaEntity transferencia = transferenciaService.getTransferencia(transferenciaId);
		
		model.addAttribute("pageTitle", "Sobre Transferencia");
		model.addAttribute("transferencia", transferencia);
		return "transferencias/transferir";
	}
	
	@PostMapping(path = "/triagem/transferencia/save")
	public String saveTransferencia(@AuthenticationPrincipal SoccoriusUserDetails authUser ,TransferenciaEntity transferencia) {
		
		TransferenciaEntity savedTransferencia = transferenciaService.createTransferencias(authUser, transferencia);
		
		return "redirect:/triagem/"+savedTransferencia.getTriagem().getId()+"/transferir/"+savedTransferencia.getId();
	}
}
