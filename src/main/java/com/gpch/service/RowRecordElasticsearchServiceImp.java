package com.gpch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpch.elasticsearchrepository.RowRecordElasticsearchRepository;
import com.gpch.model.RowRecord;

@Service("rowRecordElasticsearchService")
public class RowRecordElasticsearchServiceImp implements RowRecordElasticsearchService {

	@Autowired
	RowRecordElasticsearchRepository rowRecordElasticsearchRepository;

	@Override
	public Iterable<RowRecord> getAllRecords() {
		return rowRecordElasticsearchRepository.findAll();
	}

	@Override
	public void saveRecord(RowRecord record) {
		rowRecordElasticsearchRepository.save(record);

	}

}
