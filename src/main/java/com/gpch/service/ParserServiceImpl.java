package com.gpch.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.gpch.model.RowRecord;

@Service("parserService")
public class ParserServiceImpl implements ParserService {

	@Override
	public List<RowRecord> parseExcelFile(File file) {
		List<RowRecord> records = new ArrayList<>();
		try {
			FileInputStream excelFile = new FileInputStream(file);
			HSSFWorkbook wb = new HSSFWorkbook(excelFile);
			HSSFSheet sheet = wb.getSheetAt(0);

			Iterator<Row> rows = sheet.rowIterator();
			HSSFRow row;
			rows.next();
			while (rows.hasNext()) {
				row = (HSSFRow) rows.next();
				RowRecord rowRecord = new RowRecord();
				rowRecord.setName(getCellValue(row.getCell(0)));
				rowRecord.setLastName(getCellValue(row.getCell(1)));
				rowRecord.setPhone(getCellValue(row.getCell(2)));
				records.add(rowRecord);

			}
			return records;
		} catch (Exception e) {
			return null;
		}

	}

	private String getCellValue(Cell cell) {
		if (cell != null) {
			if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				return StringUtils.capitalize(NumberToTextConverter.toText(cell.getNumericCellValue()).trim());
			} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				return StringUtils.capitalize(cell.getStringCellValue().trim());
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
				return "";
			} else {
				return "";
			}
		}
		return "";

	}

}
