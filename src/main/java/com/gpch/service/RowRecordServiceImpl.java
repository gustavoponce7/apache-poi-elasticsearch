package com.gpch.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpch.model.RowRecord;
import com.gpch.repository.RowRecordRepository;

@Service("rowRecordService")
public class RowRecordServiceImpl implements RowRecordService {
	@Autowired
	RowRecordRepository rowRecordRepository;

	public List<RowRecord> saveAllRecords(List<RowRecord> records) {
		rowRecordRepository.save(records);
		return findAll();
	}

	public List<RowRecord> findAll() {
		return rowRecordRepository.findAll();
	}

	@Override
	public RowRecord findRecordById(long id) {
		return rowRecordRepository.findOne(id);
	}

	@Override
	public void removeAllRecords() {
		rowRecordRepository.deleteAll();
	}

	@Override
	public void truncate() {
		rowRecordRepository.truncate();
	}

}
