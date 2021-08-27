package com.example.databaseTest.Services;

import com.example.databaseTest.Entities.Transaction;
import com.example.databaseTest.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private TransactionRepository trxRepo;

    @Override
    public void addNewTrx(Transaction trx) {
        trxRepo.save(trx);
    }

    @Override
    public void updateTrxStatusById(int id, Transaction updatedTransaction) {
        Transaction transaction = getTrxById(id);
        transaction.setStatus(updatedTransaction.getStatus());
//        transaction.setExecPrice(updatedTransaction.getExecPrice());
//        transaction.setQuantity(updatedTransaction.getQuantity());
//        transaction.setTicker(updatedTransaction.getTicker());
//        transaction.setTrxDatetime(updatedTransaction.getTrxDatetime());

        trxRepo.save(transaction);

    }

    @Override
    public Transaction getTrxById(int id) {
        return trxRepo.findById(id).get();
    }

    @Override
    public List<Transaction> getAllTrxByAccId(int accId) {
        List<Transaction> transactions = new ArrayList<Transaction>();
        trxRepo.findAllTrxByAccId(accId).forEach(u -> transactions.add(u));
        return transactions;
//        return trxRepo.findAllByAccId(accId);
    }

    @Override
    public void deleteTrxById(int trxId) {
        trxRepo.deleteById(trxId);
    }


}