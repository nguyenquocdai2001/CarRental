package com.carrental.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Car {
	@Id
	private String id;
	private String name;
	private String image;
	private String brand;
	private String license_plate;
	private int number_of_seats;
	private int kilometer;
	private String basic_infor;
	private String status = "Activated";
	
	public Car(String name, String image, String brand, String license_plate, int number_of_seats,
			int kilometer, String basic_infor) {
		super();
		this.name = name;
		this.image = image;
		this.brand = brand;
		this.license_plate = license_plate;
		this.number_of_seats = number_of_seats;
		this.kilometer = kilometer;
		this.basic_infor = basic_infor;
		this.status = "Activated";
	}
	
	public Car() {
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

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getLicense_plate() {
		return license_plate;
	}

	public void setLicense_plate(String license_plate) {
		this.license_plate = license_plate;
	}

	public int getNumber_of_seats() {
		return number_of_seats;
	}

	public void setNumber_of_seats(int number_of_seats) {
		this.number_of_seats = number_of_seats;
	}

	public int getKilometer() {
		return kilometer;
	}

	public void setKilometer(int kilometer) {
		this.kilometer = kilometer;
	}

	public String getBasic_infor() {
		return basic_infor;
	}

	public void setBasic_infor(String basic_infor) {
		this.basic_infor = basic_infor;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
