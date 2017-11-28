package com.gpch.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gpch.App;
import com.gpch.model.RowRecord;

@RunWith(SpringJUnit4ClassRunner.class)
public class ParseServiceTest {

	@Autowired
	ParserServiceImpl parserService;

	@Test
	public void testPareExcelFile() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("sample-data/phones.xls").getFile());
		List<RowRecord> records = parserService.parseExcelFile(file);
		assertThat(records.size()).isEqualTo(37);
	}

}
