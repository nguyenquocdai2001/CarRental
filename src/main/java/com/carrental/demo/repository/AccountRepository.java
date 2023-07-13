package com.carrental.demo.repository;

import com.carrental.demo.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> getAccountByEmail(String email);
}
