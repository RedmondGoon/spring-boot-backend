package com.example.databaseTest.DatabaseHandler;


import com.example.databaseTest.ActionType;
import com.example.databaseTest.DatabaseIdentity.PortFolio;
import com.example.databaseTest.DatabaseIdentity.TransactionHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Lazy
public class TradeHistoryServiceHandler {

    @Autowired
    JdbcTemplate template;
    @Autowired
    private TradeHistoryRepo repo;

    public TransactionHistory buildTransactioHistory(String stockticker, double price, int volume, String type, int userid){
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setActionType(type);
        transactionHistory.setPriceAtTime(price);
        transactionHistory.setTicker(stockticker);
        transactionHistory.setVolume(volume);
        transactionHistory.setUserID(userid);
        return transactionHistory;
    }
    public void insertTransactionHistory(TransactionHistory transactionHistory){
//        String sql = "INSERT INTO TRANSACTION_HISTORY (TICKER, PRICE_AT_TIME, VOLUME, ACTION_TYPE) VALUE (?,?,?,?)";
////        template.update(sql,transactionHistory.getTicker(),transactionHistory.getPriceAtTime(),
////                transactionHistory.getVolume(),
////                1);
        repo.save(transactionHistory);
    }
    public void UpdatePortFolio(){

    }


}
