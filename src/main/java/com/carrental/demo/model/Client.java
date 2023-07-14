package com.carrental.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Client {
	@Id
	private String id;
	private String name;
	private String phone;
	private String email;
	private String address;
	private String tax_code;
	
	public Client(String id, String name, String phone, String email, String address, String tax_code) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.tax_code = tax_code;
	}
	
	public Client(String name, String phone, String email, String address, String tax_code) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.tax_code = tax_code;
	}
	
	public Client() {
		super();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTax_code() {
		return tax_code;
	}
	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
	}
	
	
	
	
}	
