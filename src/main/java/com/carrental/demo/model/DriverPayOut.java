package com.carrental.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class DriverPayOut {
	private String id;
	private String date;
	private String name_driver;
	private String money;
	private String reason;
	
	public DriverPayOut() {
		super();
	}
	
	public DriverPayOut(String id, String date, String name_driver, String money, String reason) {
		super();
		this.id = id;
		this.date = date;
		this.name_driver = name_driver;
		this.money = money;
		this.reason = reason;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName_driver() {
		return name_driver;
	}

	public void setName_driver(String name_driver) {
		this.name_driver = name_driver;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	
	
	
}
