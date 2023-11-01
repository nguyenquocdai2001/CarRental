package com.carrental.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Contract {
	@Id
	private String id;
	private String id_client;
	private String id_pre_order;
	private String name_driver;
	private String name_car_license_plate_brand_number_of_seats;
	private String name_client;
	private String tax_code;
	private String address;
	private String phone;
	private String route;
	private String pick_up_at;
	private String comback_at;
	private String total_price;
	private String pre_paid_price;
	private String another_fee;
	private String indemnification;
	private String list_customer;
	private String status;
	private String date_going;
	private String date_comback;
	private String time_going;
	private String time_comback;
	private String image;
	private String id_car;
	
	public Contract() {
		super();
	}
	
	public Contract(String id, String id_client, String id_pre_order, String name_driver, String date_going, String date_comback, String time_going, String time_comback,
			String name_car_license_plate_brand_number_of_seats, String name_client, String tax_code, String address, String phone, String route, String pick_up_at, String comback_at,
			String total_price, String pre_paid_price, String another_fee, String indemnification, String list_customer, String status, String image, String id_car) {
		super();
		this.id = id;
		this.id_client = id_client;
		this.id_pre_order = id_pre_order;
		this.name_driver = name_driver;
		this.name_car_license_plate_brand_number_of_seats = name_car_license_plate_brand_number_of_seats;
		this.name_client = name_client;
		this.tax_code = tax_code;
		this.address = address;
		this.phone = phone;
		this.route = route;
		this.pick_up_at = pick_up_at;
		this.comback_at = comback_at;
		this.total_price = total_price;
		this.pre_paid_price = pre_paid_price;
		this.another_fee = another_fee;
		this.indemnification = indemnification;
		this.list_customer = list_customer;
		this.status = status;
		this.date_going = date_going;
		this.date_comback = date_comback;
		this.time_going = time_going;
		this.time_comback = time_comback;
		this.image = image;
		this.id_car = id_car;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId_pre_order() {
		return id_pre_order;
	}
	public void setId_pre_order(String id_pre_order) {
		this.id_pre_order = id_pre_order;
	}
	public String getName_driver() {
		return name_driver;
	}
	public void setName_driver(String name_driver) {
		this.name_driver = name_driver;
	}
	public String getName_car_license_plate_brand_number_of_seats() {
		return name_car_license_plate_brand_number_of_seats;
	}
	public void setName_car_license_plate_brand_number_of_seats(String name_car_license_plate_brand_number_of_seats) {
		this.name_car_license_plate_brand_number_of_seats = name_car_license_plate_brand_number_of_seats;
	}
	public String getName_client() {
		return name_client;
	}
	public void setName_client(String name_client) {
		this.name_client = name_client;
	}
	public String getTax_code() {
		return tax_code;
	}
	public void setTax_code(String tax_code) {
		this.tax_code = tax_code;
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
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
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

	public String getTotal_price() {
		return total_price;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	public String getPre_paid_price() {
		return pre_paid_price;
	}
	public void setPre_paid_price(String pre_paid_price) {
		this.pre_paid_price = pre_paid_price;
	}
	public String getAnother_fee() {
		return another_fee;
	}
	public void setAnother_fee(String another_fee) {
		this.another_fee = another_fee;
	}
	public String getIndemnification() {
		return indemnification;
	}
	public void setIndemnification(String indemnification) {
		this.indemnification = indemnification;
	}
	
	public String getList_customer() {
		return list_customer;
	}

	public void setList_customer(String list_customer) {
		this.list_customer = list_customer;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getDate_going() {
		return date_going;
	}

	public void setDate_going(String date_going) {
		this.date_going = date_going;
	}

	public String getDate_comback() {
		return date_comback;
	}

	public void setDate_comback(String date_comback) {
		this.date_comback = date_comback;
	}

	public String getTime_going() {
		return time_going;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getId_client() {
		return id_client;
	}

	public void setId_client(String id_client) {
		this.id_client = id_client;
	}

	public String getId_car() {
		return id_car;
	}

	public void setId_car(String id_car) {
		this.id_car = id_car;
	}
	
	
}
