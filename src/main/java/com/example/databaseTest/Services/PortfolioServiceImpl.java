package com.example.databaseTest.Services;

import com.example.databaseTest.Entities.Portfolio;
import com.example.databaseTest.ExternalAPI.AlphaVantage;
import com.example.databaseTest.Repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.ResponseBody;


import java.util.*;

@Service
public class PortfolioServiceImpl implements PortfolioService{
    @Autowired
    private PortfolioRepository portRepo;

//    @Autowired
//    private AlphaVantage externalAPI;

//    @Autowired
//    private AccountRepository accRepo;



//    @Override
//    public void addNewPortfolio(Portfolio portfolio){
//        portRepo.save(portfolio);
//    }


    @Override
    public Portfolio getPortfolioById(int id) {
        return portRepo.findById(id).get();
    }

    @Override
    public Portfolio getPortfolioByAccId(int accId) {
        return portRepo.findByAccountId(accId);
//        return portRepo.findByAccId(accId);
    }

    @Override
    public Map<String, Integer> getStockHoldingsByAccId(int accId) {
        return getPortfolioByAccId(accId).getHoldings();
    }


//    @Override
//    public Map<String, Integer> getStockHoldingsById(int id) {
//        Portfolio portfolio = getPortfolioById(id);
//        return portfolio.getHoldings();
//    }

    @Override
    public double getCashHoldingsById(int id) {
        Portfolio portfolio = getPortfolioById(id);
        return portfolio.getCashBalance();
    }

    @Override
    public double getCashHoldingsByAccId(int accId) {
        return  getPortfolioByAccId(accId).getCashBalance();
    }

    @Override
    public Map<String, String> getPortfolioValueByAccId(int accId, String type) {

        double cashValue = getCashHoldingsByAccId(accId);
        Map<String, Integer> stockHoldings = getStockHoldingsByAccId(accId);
        List<String> listOfStock = new ArrayList<String>();
        listOfStock.addAll(stockHoldings.keySet());
        double stockValue = 0;

        Map<String, Double> currentStockPrices = AlphaVantage.getListOfStock(listOfStock);

        for(String ticker : stockHoldings.keySet()){
            stockValue += currentStockPrices.get(ticker)*stockHoldings.get(ticker);
        }
        Map<String, String> portfolioValue = new HashMap<String, String>();

        if (type.equals("Home")){

            portfolioValue.put("account", String.valueOf(stockValue+cashValue));
            portfolioValue.put("buyingPower", String.valueOf(cashValue));
        }
        else if(type.equals("Donut")){
            portfolioValue.put("Stocks", String.valueOf(stockValue));
            portfolioValue.put("Cash", String.valueOf(cashValue));
        }

        return portfolioValue;
    }

    @Override
    public List<Map<String, String>> getChartByAccId(int accId) {
        double cashValue = getCashHoldingsByAccId(accId);
        Map<String, Integer> stockHoldings = getStockHoldingsByAccId(accId);
        List<String> listOfStock = new ArrayList<String>();
        listOfStock.addAll(stockHoldings.keySet());
        double stockValue = 0;

        Map<String, Double> currentStockPrices = AlphaVantage.getListOfStock(listOfStock);

        for(String ticker : stockHoldings.keySet()){
            stockValue += currentStockPrices.get(ticker)*stockHoldings.get(ticker);
        }
        List<Map<String, String>>  valueHistory= new ArrayList<>();


        Map<String, String> value = new HashMap<String, String>();
        value.put("x", "2017-01-06");
        value.put("y", "1750");
        valueHistory.add(value);

        Map<String, String> value2 = new HashMap<String, String>();
        value2.put("x", "2018-01-01");
        value2.put("y", "3000");
        valueHistory.add(value2);

        Map<String, String> value3 = new HashMap<String, String>();
        value3.put("x", "2018-01-06");
        value3.put("y", "750");
        valueHistory.add(value3);

        Map<String, String> value4 = new HashMap<String, String>();
        value4.put("x", "2019-01-01");
        value4.put("y", "1250");
        valueHistory.add(value4);

        Map<String, String> value5 = new HashMap<String, String>();
        value5.put("x", "2019-01-06");
        value5.put("y", "1250");
        valueHistory.add(value5);

        Map<String, String> value6 = new HashMap<String, String>();
        value6.put("x", "2020-01-01");
        value6.put("y", "1500");
        valueHistory.add(value6);

        Map<String, String> value7 = new HashMap<String, String>();
        value7.put("x", "2020-01-06");
        value7.put("y", "2000");
        valueHistory.add(value7);

        Map<String, String> value8 = new HashMap<String, String>();
        value8.put("x", "2021-01-01");
        value8.put("y", "1250");
        valueHistory.add(value8);

        Map<String, String> value9 = new HashMap<String, String>();
        value9.put("x", java.time.LocalDateTime.now().toString());
        value9.put("y", String.valueOf(stockValue+cashValue));
        valueHistory.add(value9);


        return valueHistory;
    }


    @Override
    public void updatePortfolioHoldingsById(int id, String ticker, int quantity, double cash) {
        Portfolio portfolio = getPortfolioById(id);

        double updatedCash = portfolio.getCashBalance() + cash;
        portfolio.setCashBalance(updatedCash);

        Map<String, Integer> stockHoldings = getStockHoldingsByAccId(id);
        if (stockHoldings.containsKey(ticker)){
            int updatedQuantity = stockHoldings.get(ticker) + quantity;
            stockHoldings.put(ticker, updatedQuantity);
        }
        else{
            stockHoldings.put(ticker, quantity);
        }
        portfolio.setHoldings(stockHoldings);
        portRepo.save(portfolio);
    }

    @Override
    public void updatePortfolioHoldingsByAccId(int accId, String ticker, int quantity, double cash) {
        Portfolio portfolio = getPortfolioByAccId(accId);
        double updatedCash = portfolio.getCashBalance() + cash;
        portfolio.setCashBalance(updatedCash);


        Map<String, Integer> stockHoldings = getStockHoldingsByAccId(portfolio.getAccount().getId());
        if (stockHoldings.containsKey(ticker)){
            int updatedQuantity = stockHoldings.get(ticker) + quantity;
            stockHoldings.put(ticker, updatedQuantity);
        }
        else{
            stockHoldings.put(ticker, quantity);
        }
        portfolio.setHoldings(stockHoldings);
        portRepo.save(portfolio);
    }

//    @Override
//    public void updatePortfolioHoldings(PortfolioDTO portfolioDto) {
//        Portfolio portfolio = getPortfolioByAccId(portfolioDto.getAccId());
//        portfolio.setCashBalance(portfolioDto.getCashBalance());
//        portfolio.setHoldings(portfolioDto.getHoldings());
//    }

    @Override
    public void updateCashHoldingsById(int id, double cash) {
        Portfolio portfolio = getPortfolioById(id);
        double updatedCash = portfolio.getCashBalance() + cash;
        portfolio.setCashBalance(updatedCash);
        portRepo.save(portfolio);
    }

    @Override
    public void updateCashHoldingsByAccId(int accId, double cash) {
        Portfolio portfolio = getPortfolioByAccId(accId);
        double updatedCash = portfolio.getCashBalance() + cash;
        portfolio.setCashBalance(updatedCash);
        portRepo.save(portfolio);
    }

    @Override
    public void updateStockHoldingsById(int id, String ticker, int quantity) {
        Portfolio portfolio = getPortfolioById(id);
        Map<String, Integer> holding = portfolio.getHoldings();

        if (holding.containsKey(ticker)){
            holding.put(ticker, holding.get(ticker)+quantity);
        }
        else{
            holding.put(ticker, quantity);
        }
        portRepo.save(portfolio);

    }

    @Override
    public void updateStockHoldingsByAccId(int accId, String ticker, int quantity) {
        Portfolio portfolio = getPortfolioByAccId(accId);
        Map<String, Integer> holding = portfolio.getHoldings();

        if (holding.containsKey(ticker)){
            holding.put(ticker, holding.get(ticker)+quantity);
        }
        else{
            holding.put(ticker, quantity);
        }
        portRepo.save(portfolio);

    }

//    @Override
//    public void updatePortfolioById(int id, HoldingWrapper holdingWrapper) {
//
//        Portfolio portfolio = getPortfolioById(id);
//        portfolio.setCashBalance(holdingWrapper.getCashHoldings());
//        portfolio.setHoldings(holdingWrapper.getStockHoldings());
//        portRepo.save(portfolio);
//
//    }
//
//    @Override
//    public void updatePortfolioByAccId(int accId, HoldingWrapper holdingWrapper) {
//        Portfolio portfolio = getPortfolioById(id);
//        portfolio.setCashBalance(holdingWrapper.getCashHoldings());
//        portfolio.setHoldings(holdingWrapper.getStockHoldings());
//        portRepo.save(portfolio);
//
//    }


    @Override
    public void deletePortfolioById(int id) {
        portRepo.deleteById(id);
    }
}
