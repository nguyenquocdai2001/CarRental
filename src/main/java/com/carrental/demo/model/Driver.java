package com.carrental.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Driver {
	@Id
	private String id;
	private String name;
	private String image;
	private String age;
	private String address;
	private String phone;
	private String infor;
	private String status = "Activated";
	
	
	public Driver(String name, String image, String age, String address, String phone, String infor) {
		super();
		this.name = name;
		this.image = image;
		this.age = age;
		this.address = address;
		this.phone = phone;
		this.infor = infor;
		this.status = "Activated";
	}
	
	public Driver() {
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getInfor() {
		return infor;
	}

	public void setInfor(String infor) {
		this.infor = infor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
