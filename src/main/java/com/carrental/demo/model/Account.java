package com.carrental.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Account {
    @Id
    private String id;
    private String email;
    private String password;
    private String status;
    private String role;
    
    public Account() {
       super();
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public Account(String id, String email, String password, String status, String role) {
    	this.id = id;
    	this.email = email;
        this.password = password;
        this.status = status;
        this.role = role;
       
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
    
}
