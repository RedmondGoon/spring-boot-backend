package com.example.databaseTest.DatabaseHandler;

import com.example.databaseTest.DatabaseIdentity.PortFolio;
import com.example.databaseTest.DatabaseIdentity.TransactionHistory;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;


public interface PortfolioRepo extends CrudRepository<PortFolio, Integer> {

}
