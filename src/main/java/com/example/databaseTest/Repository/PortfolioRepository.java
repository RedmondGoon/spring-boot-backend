package com.example.databaseTest.Repository;

import com.example.databaseTest.Entities.Portfolio;
import org.springframework.data.repository.CrudRepository;

public interface PortfolioRepository extends CrudRepository<Portfolio, Integer> {

//    @Query("FROM Portfolio p where p.Account.id = :accId")
    Portfolio findByAccountId(int accId);

//    Portfolio findByAccId(@Param("accId") int accId);
}

