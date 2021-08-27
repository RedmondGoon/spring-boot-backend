package com.example.databaseTest;

import java.util.List;


import com.example.databaseTest.Entities.Transaction;
import com.example.databaseTest.Entities.TransactionStatus;
import com.example.databaseTest.Repository.TransactionRepository;
import com.example.databaseTest.Services.PortfolioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class TradeSim {
    private static final Logger LOG = LoggerFactory.getLogger(TradeSim.class);

    @Autowired
    private TransactionRepository transRepo;

    @Autowired
    private PortfolioService portfolioService;
//    private TradeMongoDao tradeDao;

    @Transactional
    public List<Transaction> findTransactionsForProcessing(){
        List<Transaction> foundTransactions = transRepo.findAllByStatus(TransactionStatus.CREATED);

        for(Transaction thisTransaction: foundTransactions) {
            thisTransaction.setStatus(TransactionStatus.PROCESSING);
            transRepo.save(thisTransaction);
        }

        return foundTransactions;
    }

    @Transactional
    public List<Transaction> findTransactionsForFilling(){
        List<Transaction> foundTransactions = transRepo.findAllByStatus(TransactionStatus.PROCESSING);

        for(Transaction thisTransaction: foundTransactions) {
            if((int) (Math.random()*10) > 8) {
                thisTransaction.setStatus(TransactionStatus.REJECTED);
            }
            else {
                thisTransaction.setStatus(TransactionStatus.FILLED);

                if (thisTransaction.getActionType().toString().equals("BUY")){
                    portfolioService.updatePortfolioHoldingsByAccId(thisTransaction.getAccId(), thisTransaction.getTicker(), thisTransaction.getQuantity(), thisTransaction.getExecPrice()*-1*thisTransaction.getQuantity());
                }
                else if (thisTransaction.getActionType().toString().equals("SELL")){
                    portfolioService.updatePortfolioHoldingsByAccId(thisTransaction.getAccId(), thisTransaction.getTicker(), thisTransaction.getQuantity()*-1, thisTransaction.getExecPrice()*thisTransaction.getQuantity());
                }
            }
            transRepo.save(thisTransaction);
        }

        return foundTransactions;
    }

    @Scheduled(fixedRateString = "${scheduleRateMs:10000}")
    public void runSim() {
        LOG.debug("Main loop running!");

        int tradesForFilling = findTransactionsForFilling().size();
        LOG.debug("Found " + tradesForFilling + " trades to be filled/rejected");

        int tradesForProcessing = findTransactionsForProcessing().size();
        LOG.debug("Found " + tradesForProcessing + " trades to be processed");

    }
}