package com.carrental.demo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Bill {
	@Id
	private String id;
	private String id_client;
	private String id_contract;
	private String name_client;
	private String phone_client;
	private String tax_code;
	private String address;
	private String payment;
	private String bank_account_number;
	private String name_service;
	private String price_bf_discount;
	private String discount;
	private String price_bf_tax;
	private String tax_percent;
	private String tax_money;
	private String total_price;
	private String date;
	private String image;
	
	public Bill() {
		super();
	}
	
	public Bill(String id, String id_client, String id_contrac, String name_client, String phone_client, String tax_code, String address, String payment,
			String bank_account_number, String name_service, String price_bf_discount, String discount,
			String price_bf_tax, String tax_percent, String tax_money, String total_price, String date, String image) {
		super();
		this.id = id;
		this.id_client = id_client;
		this.id_contract = id_contrac;
		this.name_client = name_client;
		this.phone_client = phone_client;
		this.tax_code = tax_code;
		this.address = address;
		this.payment = payment;
		this.bank_account_number = bank_account_number;
		this.name_service = name_service;
		this.price_bf_discount = price_bf_discount;
		this.discount = discount;
		this.price_bf_tax = price_bf_tax;
		this.tax_percent = tax_percent;
		this.tax_money = tax_money;
		this.total_price = total_price;
		this.date = date;
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getId_contract() {
		return id_contract;
	}

	public void setId_contract(String id_contract) {
		this.id_contract = id_contract;
	}

	

	public String getName_client() {
		return name_client;
	}

	public void setName_client(String name_client) {
		this.name_client = name_client;
	}

	public String getPhone_client() {
		return phone_client;
	}

	public void setPhone_client(String phone_client) {
		this.phone_client = phone_client;
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

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getBank_account_number() {
		return bank_account_number;
	}

	public void setBank_account_number(String bank_account_number) {
		this.bank_account_number = bank_account_number;
	}

	public String getName_service() {
		return name_service;
	}

	public void setName_service(String name_service) {
		this.name_service = name_service;
	}

	public String getPrice_bf_discount() {
		return price_bf_discount;
	}

	public void setPrice_bf_discount(String price_bf_discount) {
		this.price_bf_discount = price_bf_discount;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getPrice_bf_tax() {
		return price_bf_tax;
	}

	public void setPrice_bf_tax(String price_bf_tax) {
		this.price_bf_tax = price_bf_tax;
	}

	public String getTax_percent() {
		return tax_percent;
	}

	public void setTax_percent(String tax_percent) {
		this.tax_percent = tax_percent;
	}

	public String getTax_money() {
		return tax_money;
	}

	public void setTax_money(String tax_money) {
		this.tax_money = tax_money;
	}

	public String getTotal_price() {
		return total_price;
	}

	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
	
	
	
	
}
