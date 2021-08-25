package com.example.databaseTest.DatabaseHandler;

import com.example.databaseTest.DatabaseIdentity.TransactionHistory;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionHistoryRowMapper implements RowMapper<TransactionHistory> {

    @Override
    public TransactionHistory mapRow(ResultSet rs, int rownumber ) throws SQLException{

        TransactionHistory transactionHistory =new TransactionHistory();
        transactionHistory.setUserID(rs.getInt("UserID"));
        transactionHistory.setTicker(rs.getString("ticker"));
        transactionHistory.setPriceAtTime(rs.getDouble("price_at_time"));
        transactionHistory.setQuantity(rs.getInt("volume"));
        return transactionHistory;
    }


}
