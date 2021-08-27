package com.example.databaseTest.Repository;

import com.example.databaseTest.Entities.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Integer> {

    Account findByEmail(String email);
    Account findByEmailAndPassword(String email, String password);
}