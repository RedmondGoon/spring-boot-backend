package com.example.databaseTest.DatabaseHandler;


import com.example.databaseTest.DatabaseIdentity.PortFolio;
import com.example.databaseTest.DatabaseIdentity.TransactionHistory;
import com.example.databaseTest.DatabaseIdentity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Lazy
public class TradeServiceHandler {

    @Autowired
    JdbcTemplate template;
    @Autowired
    private TradeHistoryRepo tradeHistoryRepo;
    @Autowired
    private PortfolioRepo portfolioRepo;
    @Autowired
    private UserRepository userRepo;

    public void insertTransactionHistory(String stockticker, double price, int volume, String type, int userid){
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setActionType(type);
        transactionHistory.setPriceAtTime(price);
        transactionHistory.setTicker(stockticker);
        transactionHistory.setQuantity(volume);
        transactionHistory.setUserID(userid);
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
        else if(current_portfolios.size() ==1&& type=="SELL" ){
            PortFolio this_portfolio= current_portfolios.get(0);
            int existingVolume = this_portfolio.getVolume();

            int new_volume = existingVolume-volume;
            if (new_volume==0){
                String sql = "Delete from port_folio where userid="+ userid+" and ticker = '"+ticker+"'" ;
                template.execute(sql);
            }
            else{
                String sql = "Update port_folio set volume= ? where userid= ? and ticker = ?";
                template.update(sql,new_volume,userid,ticker );
            }

        }
    }
    public void UpdateUserBalance (int userid, int volume, double price, String action){ // we have assume the all the check have to be successful before the action took place
        Optional<User> userrepose= userRepo.findById(userid);
        User user= userrepose.get();
        double current_balance = user.getBalance();
        if (action .equals("BUY")){


            user.setBalance(current_balance-(volume*price));
            userRepo.save(user);
        }
        if (action.equals("SELL")){
            user.setBalance(current_balance+(volume*price));
            userRepo.save(user);
        }

    }
    public boolean HaveEnoughBalance(int userid, int volume, double price){
        Optional<User> userrepose= userRepo.findById(userid);
        User user= userrepose.get();
        double current_balance = user.getBalance();
        if(current_balance>= (volume*price)){
            System.out.println("This user have Enough balance to buy");
            return true;
        }
        else {
            System.out.println("This user does not have Enough balance to buy");
            return false;
        }
    }

    public boolean HaveEnoughStock (int userid, int volume,String ticker){
        String sql = "select volume from port_folio where userid="+ userid+" and ticker ='"+ticker+"'";
        int currentVolume= template.queryForObject(sql, int.class);
        if (currentVolume >= volume){
            System.out.println("This user have Enough stocks to sell");
            return true;

        }
        else {
        System.out.println("This user does not have Enough stocks to sell");
        return false;
        }
    }
    public boolean CheckSellEligibility(int userid, int volume, String ticker, double price){

        return HaveEnoughStock(userid,volume,ticker) && HaveEnoughBalance(userid,volume,price);
    }
    @Transactional
    public String MakeTransaction(int userid, int volume, String ticker, double price,String actionType){
        if(HaveEnoughStock(userid,volume,ticker) && actionType.equals("SELL")){
            UpdatePortFolio(userid,ticker,volume,actionType,price);
            UpdateUserBalance(userid,volume,price,actionType);
            insertTransactionHistory(ticker,price,volume,actionType,userid);
            return "Sell Successful";
        }
        else if(HaveEnoughBalance(userid,volume,price)&& actionType.equals("BUY")){
            UpdatePortFolio(userid,ticker,volume,actionType,price);
            UpdateUserBalance(userid,volume,price,actionType);
            insertTransactionHistory(ticker,price,volume,actionType,userid);
            return "Buy Successful";
        }
        else {
            return "Transaction unsucessful";
        }

    }

}
