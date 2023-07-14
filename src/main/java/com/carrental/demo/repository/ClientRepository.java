package com.carrental.demo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.carrental.demo.model.Client;

public interface ClientRepository extends MongoRepository<Client, String>{
	Optional<Client> getClientByEmail(String email);
}
