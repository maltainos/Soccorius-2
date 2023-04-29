package com.soccoriusapp.mvc.service.exporter.users;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.soccoriusapp.mvc.entity.UserEntity;
import com.soccoriusapp.mvc.service.exporter.AbstractExporter;

public class UsersPdfExporter extends AbstractExporter {

	public void export(List<UserEntity> users, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "application/pdf", ".pdf", "users_");
		
	//	KidsPdfExporter kidsExport = new KidsPdfExporter();
	//	kidsExport.exporter(response);
		
		
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		 
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18); font.setColor(Color.BLUE);
		  
		Paragraph paragrah = new Paragraph("List de Utilizadores SIGCSMI", font);
		paragrah.setAlignment(Element.ALIGN_CENTER);
		  
		PdfPTable table = new PdfPTable(7); table.setWidthPercentage(100f);
		table.setSpacingBefore(15f); table.setWidths(new float[] { 0.8f, 3.5f, 3.5f,
		 2.0f, 2.5f, 1.3f, 1.9f });
		 
		writeTableHeader(table); writeTableData(table, users);
		 
		document.open(); document.add(paragrah); document.add(table);
		document.addCreationDate(); document.close();
	}

	private void writeTableHeader(PdfPTable table) {

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5f);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("#", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("E-mail", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Nome", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Apelido", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Nivel Acesso", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Activo", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Criado", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table, List<UserEntity> users) {

		int rowCount = 1;
		for (UserEntity user : users) {

			table.addCell(String.valueOf(rowCount));
			table.addCell(user.getEmail());
			table.addCell(user.getFirstName());
			table.addCell(user.getLastName());
			//table.addCell(user.getGroups().toString());
			//table.addCell(String.valueOf(user.isStatus()));
			int month = user.getCreatedOn().getMonthValue();
			String date;

			if (month > 9)
				date = user.getCreatedOn().getDayOfMonth() + "/" + month + "/" + user.getCreatedOn().getYear();
			else
				date = user.getCreatedOn().getDayOfMonth() + "/0" + month + "/" + user.getCreatedOn().getYear();
			table.addCell(date);
			rowCount++;
		}

	}
}
