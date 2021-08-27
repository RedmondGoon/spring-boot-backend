package com.example.databaseTest.Services;

import com.example.databaseTest.Entities.Portfolio;

import java.util.List;
import java.util.Map;

public interface PortfolioService {

    Portfolio getPortfolioById(int id);
    Portfolio getPortfolioByAccId(int accId);
    Map<String, Integer> getStockHoldingsByAccId(int accId);
    double getCashHoldingsByAccId(int accId);
//    void addNewPortfolio(Portfolio portfolio);
//    void addNewPortfolioByAccId(int accId);
//    Map<String, Integer> getStockHoldingsById(int id);
    double getCashHoldingsById(int id);
    Map<String, String> getPortfolioValueByAccId(int accId, String type);
    List<Map<String, String>> getChartByAccId(int accId);
    List<List<String>> getStockBreakdownByAccId(int accId);

    void updatePortfolioHoldingsById(int id, String ticker, int quantity, double cash);
    void updatePortfolioHoldingsByAccId(int accId, String ticker, int quantity, double cash);
//    void updatePortfolioHoldings(PortfolioDTO portfolioDto);
    boolean updateCashHoldingsById(int id, double cash);
    boolean updateCashHoldingsByAccId(int accId, double cash);
    void updateStockHoldingsById(int id, String ticker, int quantity);
    void updateStockHoldingsByAccId(int accId, String ticker, int quantity);


    void deletePortfolioById(int id);

}
