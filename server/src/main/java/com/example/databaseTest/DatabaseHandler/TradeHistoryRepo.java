package com.example.databaseTest.DatabaseHandler;

import com.example.databaseTest.DatabaseIdentity.TransactionHistory;
import org.springframework.data.repository.CrudRepository;

public interface TradeHistoryRepo extends CrudRepository<TransactionHistory, Integer> {
}
