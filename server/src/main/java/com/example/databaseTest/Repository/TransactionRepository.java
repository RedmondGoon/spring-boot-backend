package com.example.databaseTest.Repository;

import com.example.databaseTest.Entities.Transaction;
import com.example.databaseTest.Entities.TransactionStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    List<Transaction> findAllTrxByAccId(int accId);
    List<Transaction> findAllByStatus(TransactionStatus status);

}