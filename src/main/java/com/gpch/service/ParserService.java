package com.gpch.service;

import java.io.File;
import java.util.List;

import com.gpch.model.RowRecord;

public interface ParserService {
	
	public List<RowRecord> parseExcelFile(File file);

}
