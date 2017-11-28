package com.gpch.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.gpch.model.RowRecord;

@Repository("rowRecordRepositoryJDBC")
public class RowRecordRepositoryJDBCImpl implements RowRecordRepositoryJDBC {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	DataSource dataSource;

	@Override
	public void saveAll(List<RowRecord> records) {
		String sql = "insert into row_record (name, last_name, phone) values (?, ?, ?)";
		final int batchSize = 500;
		List<List<RowRecord>> batchLists = Lists.partition(records, batchSize);

		for (List<RowRecord> batch : batchLists) {
			jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					RowRecord record = batch.get(i);
					ps.setString(1, record.getName());
					ps.setString(2, record.getLastName());
					ps.setString(3, record.getPhone());
					
				}

				@Override
				public int getBatchSize() {
					return batch.size();
				}
			});
		}

	}

	@Override
	public void save(RowRecord record) {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;

		try {
			dbConnection = dataSource.getConnection();
			String sql = "insert into row_record (name, last_name, phone) values (?, ?, ?)";

			preparedStatement = dbConnection.prepareStatement(sql);
			preparedStatement.setString(1, record.getName());
			preparedStatement.setString(2, record.getLastName());
			preparedStatement.setString(3, record.getPhone());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQLException: " + e);
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void saveAllRecordsPS(List<RowRecord> records) {
		Connection dbConnection = null;
		PreparedStatement preparedStatement = null;
		try {

			dbConnection = dataSource.getConnection();
			String sql = "insert into row_record (name, last_name, phone) values (?, ?, ?)";

			preparedStatement = dbConnection.prepareStatement(sql);

			for (RowRecord record : records) {
				preparedStatement.setString(1, record.getName());
				preparedStatement.setString(2, record.getLastName());
				preparedStatement.setString(3, record.getPhone());
				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {

		}finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (dbConnection != null) {
					dbConnection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
