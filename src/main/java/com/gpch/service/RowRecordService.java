package com.gpch.service;

import java.util.List;

import com.gpch.model.RowRecord;

public interface RowRecordService {
	public List<RowRecord> saveAllRecords(List<RowRecord> records);
	public List<RowRecord> findAll();
	public RowRecord findRecordById(long id);
	public void removeAllRecords();
	public void truncate();

}
