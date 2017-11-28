package com.gpch.model;

import javax.persistence.Entity;

import org.springframework.data.elasticsearch.annotations.Document;

@Entity
@Document(indexName = "records", type = "rowrecord")
public class RowRecord extends BaseEntity {
	private String name;
	private String lastName;
	private String phone;

	public RowRecord() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
