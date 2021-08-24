package com.example.databaseTest;

import com.example.databaseTest.DatabaseIdentity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository <User, Integer> {

}
