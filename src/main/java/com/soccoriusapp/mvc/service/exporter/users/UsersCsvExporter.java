package com.soccoriusapp.mvc.service.exporter.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.soccoriusapp.mvc.entity.UserEntity;
import com.soccoriusapp.mvc.service.exporter.AbstractExporter;

public class UsersCsvExporter extends AbstractExporter {

	public void export(List<UserEntity> users, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "text/csv", ".csv", "users_");

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		String[] csvHeader = { "E-mail", "Nome", "Apelido", "Niveis Acesso", "Ativo", "Criado Em", "Actualizado Em" };
		String[] fieldMapping = { "email", "firstName", "lastName", "groups", "status", "createdOn", "updatedOn" };
		csvWriter.writeHeader(csvHeader);

		for (UserEntity user : users)
			csvWriter.write(user, fieldMapping);

		csvWriter.close();
	}

}
