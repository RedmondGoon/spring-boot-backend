package com.example.databaseTest.Services;

import com.example.databaseTest.Entities.Portfolio;

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
    double getPortfolioValueById(int id);
    void updatePortfolioHoldingsById(int id, String ticker, int quantity, double cash);
    void updatePortfolioHoldingsByAccId(int accId, String ticker, int quantity, double cash);
//    void updatePortfolioHoldings(PortfolioDTO portfolioDto);
    void updatedCashHoldingsById(int id, double cash);
    void updatedCashHoldingsByAccId(int accId, double cash);
    void updatedStockHoldingsById(int id, String ticker, int quantity);
    void updatedStockHoldingsByAccId(int accId, String ticker, int quantity);

    void deletePortfolioById(int id);

}
