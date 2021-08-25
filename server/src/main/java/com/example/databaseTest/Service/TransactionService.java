package com.example.databaseTest.Service;

import com.example.databaseTest.DatabaseHandler.TradeHistoryRepo;
import com.example.databaseTest.DatabaseHandler.TradeServiceHandler;
import com.example.databaseTest.DatabaseHandler.UserRepository;
import com.example.databaseTest.DatabaseIdentity.TransactionHistory;
import com.example.databaseTest.DatabaseIdentity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionService {

    @Autowired
    private TradeServiceHandler repo;

    @Autowired
    private TradeHistoryRepo repo2;

    @Autowired
    private UserRepository userRepository;

    public void doDemo(){

        String name ="Huang";
        String email="asdfasdf";
        User n = new User();
        int userid = 1;
        n.setId(userid);
        n.setName(name);
        n.setEmail(email);
        n.setBalance(10000000.0);
        userRepository.save(n);

        String ticker ="IBM";

        double price = 110.3;
        String type= "BUY";
        int volume = 1000;
        repo.insertTransactionHistory(ticker,price,volume,type,userid);
//        repo.insertTransactionHistory(transactionHistory);
        boolean haveEnough= repo.CheckSellEligibility(userid, volume,ticker,  price);
        System.out.println(haveEnough);

        repo.UpdatePortFolio(userid,ticker,volume,type,price);
        repo.UpdatePortFolio(userid,ticker,volume,type,price);

        repo.MakeTransaction(userid, volume, ticker, price,type);
    }


}
