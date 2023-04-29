package com.soccoriusapp.mvc.service.exporter.users;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.soccoriusapp.mvc.entity.UserEntity;
import com.soccoriusapp.mvc.service.exporter.AbstractExporter;

public class UsersExcelExporter extends AbstractExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public UsersExcelExporter() {
		workbook = new XSSFWorkbook();
	}

	private void createCell(XSSFRow row, int columnIndex, Object value, CellStyle cellStyle) {

		XSSFCell cell = row.createCell(columnIndex);
		sheet.autoSizeColumn(columnIndex);

		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}

	}

	private void writeHeaderLine() {

		sheet = workbook.createSheet("Users");
		XSSFRow row = sheet.createRow(0);

		XSSFCellStyle cellStype = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		cellStype.setFont(font);

		createCell(row, 0, "E-mail", cellStype);
		createCell(row, 1, "Primeiro Nome", cellStype);
		createCell(row, 2, "Apelido", cellStype);
		createCell(row, 3, "Niveis Acesso", cellStype);
		createCell(row, 4, "Ativo", cellStype);
		createCell(row, 5, "Criado Em", cellStype);
		createCell(row, 6, "Actualizado Em", cellStype);
	}

	private void writeDataLines(List<UserEntity> users) {

		int rowIndex = 1;

		XSSFCellStyle cellStype = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		cellStype.setFont(font);

		for (UserEntity user : users) {

			XSSFRow row = sheet.createRow(rowIndex++);
			int columnIndex = 0;

			createCell(row, columnIndex++, user.getEmail(), cellStype);
			createCell(row, columnIndex++, user.getFirstName(), cellStype);
			createCell(row, columnIndex++, user.getLastName(), cellStype);
			//createCell(row, columnIndex++, user.getGroups().toString(), cellStype);
			//createCell(row, columnIndex++, user.isStatus(), cellStype);
			createCell(row, columnIndex++, user.getCreatedOn().toString(), cellStype);
			createCell(row, columnIndex++, user.getUpdatedOn() != null ? user.getUpdatedOn().toString() : "",
					cellStype);
		}
	}

	public void export(List<UserEntity> users, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "application/octet-stream", ".xlsx", "users_");

		writeHeaderLine();
		writeDataLines(users);

		ServletOutputStream outpuStream = response.getOutputStream();
		workbook.write(outpuStream);
		workbook.close();
		outpuStream.close();
	}

}
