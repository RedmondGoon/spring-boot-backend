package com.example.databaseTest.DatabaseHandler;


import com.example.databaseTest.DatabaseIdentity.PortFolio;
import com.example.databaseTest.DatabaseIdentity.TransactionHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Lazy
public class TradeHistoryServiceHandler {

    @Autowired
    JdbcTemplate template;
    @Autowired
    private TradeHistoryRepo tradeHistoryRepo;
    @Autowired
    private PortfolioRepo portfolioRepo;

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
        tradeHistoryRepo.save(transactionHistory);
    }
    public void UpdatePortFolio(int userid, String ticker, int volume, String type, double priceBought){

        String checkquery = "Select * from port_folio where ticker = ? and userid= ?";
        List<PortFolio> current_portfolios =  template.query(checkquery,new PortFolioRowMapper(), ticker, userid);
        if (current_portfolios.size() ==0&& type=="BUY"){
            PortFolio this_portfolio=  new PortFolio();
            this_portfolio.setUserid(userid);
            this_portfolio.setTicker(ticker);
            this_portfolio.setVolume(volume);
            portfolioRepo.save(this_portfolio);
        }
        else if(current_portfolios.size() ==1&& type=="BUY"){
            PortFolio this_portfolio= current_portfolios.get(0);
            int existingVolume = this_portfolio.getVolume();
            String sql = "Update port_folio set volume= ? where userid= ? and ticker = ?";
            template.update(sql,existingVolume+volume,userid,ticker );
        }
    }
    public void UpdateUserBalance (int userid, int volume, double price, ){ // we have assume the all the check have to be successful before the action took place


    }



}
