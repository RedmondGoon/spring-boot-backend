package com.example.databaseTest.Services;

import com.example.databaseTest.Entities.Portfolio;
import com.example.databaseTest.Repository.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PortfolioServiceImpl implements PortfolioService{
    @Autowired
    private PortfolioRepository portRepo;

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
    public double getPortfolioValueById(int id) {
        return 0;
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
    public void updatedCashHoldingsById(int id, double cash) {
        Portfolio portfolio = getPortfolioById(id);
        portfolio.setCashBalance(cash);
        portRepo.save(portfolio);
    }

    @Override
    public void updatedCashHoldingsByAccId(int accId, double cash) {
        Portfolio portfolio = getPortfolioByAccId(accId);
        portfolio.setCashBalance(cash);
        portRepo.save(portfolio);
    }

    @Override
    public void updatedStockHoldingsById(int id, String ticker, int quantity) {
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
    public void updatedStockHoldingsByAccId(int accId, String ticker, int quantity) {
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
