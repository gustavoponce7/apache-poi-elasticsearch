package com.gpch.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gpch.model.RowRecord;

@Repository("rowRecordRepository")
public interface  RowRecordRepository extends JpaRepository<RowRecord, Long>{
	@Modifying
	@Transactional
	@Query(value = "TRUNCATE row_record", nativeQuery = true)
	void truncate();
}
