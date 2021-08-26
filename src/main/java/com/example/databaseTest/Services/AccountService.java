package com.example.databaseTest.Services;

import com.example.databaseTest.Entities.Account;

import java.util.Map;

public interface AccountService {

    Account getAccountById(int id);
    Account getAccountByEmail(String email);
    Map<String, String> getAccountDetailsByEmailAndPassword(String email, String password);

    void addNewAccount(Account account);

    void updateAccountById(int id, Account updatedAccount);
    void updateAccountByEmail(String email, Account updatedAccount);
    void deleteAccountById(int id);
}
