package com.gpch.service;

import com.gpch.model.RowRecord;

public interface RowRecordElasticsearchService {
	Iterable<RowRecord> getAllRecords();
	void saveRecord(RowRecord record);
}
