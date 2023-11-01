package com.carrental.demo.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class CarPayOut {
	private String id;
	private String date;
	private String name_car;
	private String money;
	private String reason;
	
	public CarPayOut() {
		super();
	}
	
	public CarPayOut(String id, String date, String name_car, String money, String reason) {
		super();
		this.id = id;
		this.date = date;
		this.name_car = name_car;
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

	public String getName_car() {
		return name_car;
	}

	public void setName_car(String name_car) {
		this.name_car = name_car;
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