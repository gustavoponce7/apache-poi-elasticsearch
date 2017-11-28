package com.gpch.repository;

import java.util.List;

import com.gpch.model.RowRecord;

public interface RowRecordRepositoryJDBC {
	void saveAll(List<RowRecord> records);
	void save(RowRecord records);
	void saveAllRecordsPS(List<RowRecord> records);

}
