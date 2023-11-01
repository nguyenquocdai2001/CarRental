package com.carrental.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class PreOrder {
	@Id
	private String id;
	private String pick_up_at;
	private String comback_at;
	private String address;
	private String time_pre_order;
	private String date_going;
	private String date_comback;
	private String time_going;
	private String time_comback;
	private String id_client;
	private String name_client;
	private String phone_client;
	private String name_car_license_plate_brand_number_of_seats;
	private String route;
	private String name_driver;
	private String total_price;
	private String status;
	
	public PreOrder(String id, String pick_up_at, String comback_at, String address, String time_pre_order, String date_going, String date_comback, String time_going, String time_comback, String id_client, String name_client,
			String name_car_license_plate_brand_number_of_seats, String phone_client, String route, String name_driver,
			String total_price, String status) {
		super();
		this.id = id;
		this.pick_up_at = pick_up_at;
		this.comback_at = comback_at;
		this.address = address;
		this.time_pre_order = time_pre_order;
		this.date_going = date_going;
		this.date_comback = date_comback;
		this.time_going = time_going;
		this.time_comback = time_comback;
		this.id_client = id_client;
		this.name_client = name_client;
		this.name_car_license_plate_brand_number_of_seats = name_car_license_plate_brand_number_of_seats;
		this.phone_client = phone_client;
		this.route = route;
		this.name_driver = name_driver;
		this.total_price = total_price;
		this.status = status;
	}
	

	public PreOrder() {
		super();
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getPick_up_at() {
		return pick_up_at;
	}


	public void setPick_up_at(String pick_up_at) {
		this.pick_up_at = pick_up_at;
	}
	
	


	public String getComback_at() {
		return comback_at;
	}


	public void setComback_at(String comback_at) {
		this.comback_at = comback_at;
	}
	

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getTime_pre_order() {
		return time_pre_order;
	}

	public void setTime_pre_order(String time_pre_order) {
		this.time_pre_order = time_pre_order;
	}
	
	

	public String getDate_going() {
		return date_going;
	}

	public void setDate_going(String date_going) {
		this.date_going = date_going;
	}

	public String getTime_going() {
		return time_going;
	}
	
	

	public String getDate_comback() {
		return date_comback;
	}


	public void setDate_comback(String date_comback) {
		this.date_comback = date_comback;
	}


	public void setTime_going(String time_going) {
		this.time_going = time_going;
	}
	
	

	public String getTime_comback() {
		return time_comback;
	}


	public void setTime_comback(String time_comback) {
		this.time_comback = time_comback;
	}


	public String getId_client() {
		return id_client;
	}

	public void setId_client(String id_client) {
		this.id_client = id_client;
	}

	public String getName_client() {
		return name_client;
	}

	public void setName_client(String name_client) {
		this.name_client = name_client;
	}

	

	public String getName_car_license_plate_brand_number_of_seats() {
		return name_car_license_plate_brand_number_of_seats;
	}


	public void setName_car_license_plate_brand_number_of_seats(String name_car_license_plate_brand_number_of_seats) {
		this.name_car_license_plate_brand_number_of_seats = name_car_license_plate_brand_number_of_seats;
	}


	public String getPhone_client() {
		return phone_client;
	}

	public void setPhone_client(String phone_client) {
		this.phone_client = phone_client;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}


	public String getName_driver() {
		return name_driver;
	}

	public void setName_driver(String name_driver) {
		this.name_driver = name_driver;
	}

	public String getTotal_price() {
		return total_price;
	}

	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
