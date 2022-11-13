package com.soccoriusapp.mvc.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.soccoriusapp.mvc.entity.InternadoEntity;
import com.soccoriusapp.mvc.entity.Triagem;
import com.soccoriusapp.mvc.security.SoccoriusUserDetails;
import com.soccoriusapp.mvc.service.impl.InternadoServiceImpl;
import com.soccoriusapp.mvc.service.impl.TriagemServiceImpl;

@Controller
public class InternadoController {
	
	@Autowired
	private TriagemServiceImpl triagemService;
	
	@Autowired
	private InternadoServiceImpl internadoService;
	
	@RequestMapping(value = "internados", method = RequestMethod.GET)
	public String showTransferenciaFirstPage(Model model) {
		return showInternadosPerPage(1, 10,"id","asc","", model);
	}

	@RequestMapping(value = "internados/page/{page}", method = RequestMethod.GET)
	public String showInternadosPerPage(@PathVariable int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "sortColumn", defaultValue = "id") String sortColumn,
			@RequestParam(name = "sortMode", defaultValue = "asc") String sortMode,
			@RequestParam(name = "keyword", defaultValue = "") String keyword, Model model) {

		Page<InternadoEntity> internados = internadoService.getInternados(page, limit, sortColumn,
				sortMode);

		model.addAttribute("pageTitle", "Pacientes Internados");
		model.addAttribute("currentPage", page);
		model.addAttribute("limit", limit);
		model.addAttribute("keyword", keyword);
		model.addAttribute("sortColumn", sortColumn);
		model.addAttribute("sortMode", sortMode);
		String reverseSortMode = sortMode.equalsIgnoreCase("asc") ? "desc" : "asc";
		model.addAttribute("reverseSortMode", reverseSortMode);
		model.addAttribute("internados", internados.getContent());
		return "internados/internados";
	}
	
	@RequestMapping(value = "/paciente/{pCode}/triagem/{tId}/internar", method = RequestMethod.GET)
	public String showInternarPacientePage(@AuthenticationPrincipal SoccoriusUserDetails authUser, 
			@PathVariable String pCode, @PathVariable Integer tId, Model model) {
		
		Triagem triagem = triagemService.getTriagem(tId);
		InternadoEntity internado = new InternadoEntity();
		internado.setTriagem(triagem);
		internado.setPaciente(triagem.getPaciente());
		internado.setMedicoResponsavel(authUser.getUser());
		
		model.addAttribute("pageTitle", "Internar Paciente");
		model.addAttribute("internado", internado);
		
		return "internados/internar";
	}
	
	@RequestMapping(value = "/internar/save", method = RequestMethod.POST)
	public String showTransferirPage(@AuthenticationPrincipal SoccoriusUserDetails authUser, 
			InternadoEntity internado, RedirectAttributes attributes) {
		
		InternadoEntity savedInternado = internadoService.internar(authUser.getUser(), internado);
		attributes.addFlashAttribute("message", "Paciente "+savedInternado.getPaciente()
				.getCodeFullNameAge()+" internado com Sucesso!");
		return "redirect:/internados/page/1?keyword="+savedInternado.getInternadoId();
	}
}
