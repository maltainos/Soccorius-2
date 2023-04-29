package com.soccoriusapp.mvc.controller.wsapi;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccoriusapp.mvc.entity.UserEntity;
import com.soccoriusapp.mvc.service.exporter.users.UsersCsvExporter;
import com.soccoriusapp.mvc.service.exporter.users.UsersExcelExporter;
import com.soccoriusapp.mvc.service.exporter.users.UsersPdfExporter;
import com.soccoriusapp.mvc.service.impl.UserServiceImpl;

@RestController
@RequestMapping(path = "/wsapi/users")
public class UserRestController {

	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping
	public boolean checkUserUnique(@Param("id") Integer id, @Param("email") String email) {
		
		return userService.checkUserUnique(id, email);
	}
	
	@GetMapping(path = "/export/csv")
	//@Secured("ROLE_EXPORT_USER_CSV")
	//@ApiOperation(value = "exporta documento em formato CSV")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		List<UserEntity> users = userService.findAll();
		UsersCsvExporter exporter = new UsersCsvExporter();
		exporter.export(users, response);
	}

	@GetMapping(path = "/export/pdf")
	//@Secured("ROLE_EXPORT_USER_PDF")
	//@ApiOperation(value = "exporta documento em formato PDF")
	public void exportToPDF(HttpServletResponse response) throws IOException {

		System.out.println("\n\n\nPDF USERS\n\n\n");
		List<UserEntity> users = userService.findAll();
		UsersPdfExporter exporter = new UsersPdfExporter();
		exporter.export(users, response);
	}

	@GetMapping(path = "/export/excel")
	//@Secured("ROLE_EXPORT_USER_EXCEL")
	//@ApiOperation(value = "exporta documento em formato EXCEL")
	public void exportToEXCEL(HttpServletResponse response) throws IOException {

		List<UserEntity> users = userService.findAll();
		UsersExcelExporter exporter = new UsersExcelExporter();
		exporter.export(users, response);
	}


}
