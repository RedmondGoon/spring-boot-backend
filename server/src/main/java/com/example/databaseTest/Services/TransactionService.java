package com.example.databaseTest.Services;

import com.example.databaseTest.Entities.Transaction;

import java.util.List;

public interface TransactionService {


    void addNewTrx(Transaction trx);
    void updateTrxStatusById(int trxId, Transaction updatedTransaction);
    Transaction getTrxById(int id);
    List<Transaction> getAllTrxByAccId(int accId);
    void deleteTrxById(int trxId);

}
