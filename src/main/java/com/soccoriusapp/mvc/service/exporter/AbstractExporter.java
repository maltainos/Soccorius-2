package com.soccoriusapp.mvc.service.exporter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

public class AbstractExporter {

	public void setResponseHeader(HttpServletResponse response, String contentType, String fileExtension, String file)
			throws IOException {

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String timestamp = dateFormatter.format(new Date());
		String fileName = file + timestamp + fileExtension;

		response.setContentType(contentType);

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + fileName;
		response.addHeader(headerKey, headerValue);

	}
}
