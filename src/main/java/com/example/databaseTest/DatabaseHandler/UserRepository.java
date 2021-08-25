package com.example.databaseTest.DatabaseHandler;

import com.example.databaseTest.DatabaseIdentity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface UserRepository extends CrudRepository <User, Integer> {



}
