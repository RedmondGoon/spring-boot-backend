package com.example.databaseTest.Service;

import com.example.databaseTest.DatabaseHandler.TradeHistoryRepo;
import com.example.databaseTest.DatabaseHandler.TradeHistoryServiceHandler;
import com.example.databaseTest.DatabaseIdentity.TransactionHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionService {

    @Autowired
    private TradeHistoryServiceHandler repo;

    @Autowired
    private TradeHistoryRepo repo2;
    public void doDemo(){
        String ticker ="IBM";
        double price = 110.3;
        String type= "BUY";
        int volume = 1000;
        TransactionHistory transactionHistory= repo.buildTransactioHistory(ticker,price,volume,type);
//        repo.insertTransactionHistory(transactionHistory);
        repo2.save(transactionHistory);


    }


}
