package com.gpch;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.gpch.model.RowRecord;
import com.gpch.repository.RowRecordRepositoryJDBC;
import com.gpch.service.ParserService;
import com.gpch.service.RowRecordElasticsearchService;
import com.gpch.service.RowRecordService;

@SpringBootApplication
@EnableJpaRepositories("com.gpch.repository")
@EnableElasticsearchRepositories("com.gpch.elasticsearchrepository")
public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	@Value(value = "classpath:sample-data/phones.xls")
	private Resource excelFile;
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Bean
	public CommandLineRunner setup(ParserService parserService, RowRecordService rowRecordService,
			RowRecordElasticsearchService rowRecordElasticsearchService, RowRecordRepositoryJDBC rowRecordRepositoryJDBC) {
		return (args) -> {
			//Loading the file from resources folder
			List<RowRecord> records = parserService.parseExcelFile(excelFile.getFile());
	
			LocalDateTime time;
			//Batch
			rowRecordService.truncate();
			logger.info("###############Starting Batch###############");
			time = LocalDateTime.now();
			rowRecordRepositoryJDBC.saveAll(records); //JDBC batchUpdate
			logger.info("Batch: " + Duration.between(time, LocalDateTime.now()).toString());
			
			//JPA
			rowRecordService.truncate();
			time = LocalDateTime.now();
			logger.info("###############Starting JPA###############");
			rowRecordService.saveAllRecords(records); //JPA
			logger.info("JPA: " + Duration.between(time, LocalDateTime.now()).toString());
			
			//PreparedStatment
			rowRecordService.truncate();
			logger.info("###############Starting PreparedStatement###############");
			time = LocalDateTime.now();
			//records.forEach(record -> rowRecordRepositoryJDBC.save(record)); //Save PreparedStatment
			rowRecordRepositoryJDBC.saveAllRecordsPS(records);
			logger.info("PreparedStatment: " + Duration.between(time, LocalDateTime.now()).toString());
			
			logger.info("############Data From the file has been stored in the database###########");
			
			for (RowRecord record : records) {
				rowRecordElasticsearchService.saveRecord(record); //Sync database against Elasticsearch
			}
			logger.info("Data From the file has been stored in the Elasticsearch cluster");

		};
	}

}
