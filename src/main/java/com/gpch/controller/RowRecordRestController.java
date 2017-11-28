package com.gpch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gpch.model.RowRecord;
import com.gpch.service.RowRecordElasticsearchService;
import com.gpch.service.RowRecordService;

@RestController
public class RowRecordRestController {
	
	@Autowired
	private RowRecordService rowRecordService;
	@Autowired
	private RowRecordElasticsearchService rowRecordElasticsearchService;
	
	
	@RequestMapping(path="/records", method=RequestMethod.GET)
	public List<RowRecord> getAllRecords(){
		return rowRecordService.findAll();
	}
	
	@RequestMapping(path="/records-elasticsearch", method=RequestMethod.GET)
	public Iterable<RowRecord> getAllRecordsByElasticsearch(){
		return rowRecordElasticsearchService.getAllRecords();
	}


}
