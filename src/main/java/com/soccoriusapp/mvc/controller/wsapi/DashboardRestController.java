package com.soccoriusapp.mvc.controller.wsapi;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccoriusapp.mvc.model.rest.DashboardCaseRest;
import com.soccoriusapp.mvc.model.rest.ReportIncomeWeklyRest;
import com.soccoriusapp.mvc.model.rest.ReportRest;
import com.soccoriusapp.mvc.model.rest.TriagemRest;
import com.soccoriusapp.mvc.service.impl.EncaminhaServiceImpl;
import com.soccoriusapp.mvc.service.impl.InternadoServiceImpl;
import com.soccoriusapp.mvc.service.impl.LaboratorioServiceImpl;
import com.soccoriusapp.mvc.service.impl.ObitoServiceImpl;
import com.soccoriusapp.mvc.service.impl.PacienteServiceImpl;
import com.soccoriusapp.mvc.service.impl.TransferenciaServiceImpl;
import com.soccoriusapp.mvc.service.impl.TriagemServiceImpl;
import com.soccoriusapp.mvc.service.impl.UserServiceImpl;

@RestController
@RequestMapping(path = "/wsapi/dashboard")
public class DashboardRestController {
	
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
	public ReportRest showHomePage() {
	
		ReportRest report  = new ReportRest();
		
		DashboardCaseRest user = new DashboardCaseRest("users", userService.countUser());
		DashboardCaseRest paciente = new DashboardCaseRest("pacientes", pacienteService.countPacientes());
		DashboardCaseRest triagem = new DashboardCaseRest("triagens", triagemService.countTriagens());
		DashboardCaseRest internados = new DashboardCaseRest("internados", internadoService.countInternados());
		DashboardCaseRest transferencias = new DashboardCaseRest("transferencias", transferenciaService.countTransferencias());
		DashboardCaseRest obitos = new DashboardCaseRest("obitos", obitoService.countObitos());
		DashboardCaseRest receitas = new DashboardCaseRest("receitas", 0l);
		DashboardCaseRest laboratorios = new DashboardCaseRest("laboratorios", laboratorioService.countLaboratorios());
		DashboardCaseRest encaminhados = new DashboardCaseRest("encaminhados", encaminhadoService.countEncaminhados());
		
		report.addAll(List.of(user, paciente, triagem, internados, transferencias, obitos, receitas, laboratorios, encaminhados));
		
		return report;
	}
	
	@GetMapping(path = "/week")
	public ReportRest lastWeekIncomeData() {
		ReportRest report  = new ReportRest();
		List<ReportIncomeWeklyRest> reportWeekly = pacienteService.getWeeklyIncome(LocalDate.of(2022,01,01), LocalDate.now());
		reportWeekly.forEach(week -> {
			report.add(new DashboardCaseRest(week.getDayOfWeek(), week.getIncomeQtd()));
		});
		return report;
	}
	
	@GetMapping("/{annual}")
	public ReportRest getYearReportPerPage() {
		LocalDate formatDateStart = LocalDate.of(2022, 01, 01);
		LocalDate formatDateEnd = formatDateStart.plusYears(1);
		List<TriagemRest> triagemValue = triagemService.getTriagemReport(formatDateStart, formatDateEnd);
		ReportRest returnValue  = new ReportRest();
		triagemValue.forEach(annual ->{
			returnValue.add(new DashboardCaseRest(annual.getData().toString(), annual.getTotalData()));
		});
		return returnValue;
	}

}
