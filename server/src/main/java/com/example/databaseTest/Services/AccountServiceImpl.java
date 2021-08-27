package com.example.databaseTest.Services;


import com.example.databaseTest.Repository.AccountRepository;
import com.example.databaseTest.Entities.Account;
import com.example.databaseTest.Entities.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountRepository accRepo;

    @Override
    public Account getAccountById(int id) {
        return accRepo.findById(id).get();
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accRepo.findByEmail(email);
    }

    @Override
    public Map<String, String> getAccountDetailsByEmailAndPassword(String email, String password) {
        Account account = accRepo.findByEmailAndPassword(email, password);
        Map<String, String> accDetails = new HashMap<String, String>();
        accDetails.put("accId", Integer.toString(account.getId()));
        accDetails.put("email", account.getEmail());
        return accDetails;
    }

    @Override
    public void addNewAccount(Account account) {
        Portfolio portfolio = new Portfolio(new HashMap<String, Integer>()
                ,0);
        account.setPortfolio(portfolio);
        accRepo.save(account);
    }

    @Override
    public void updateAccountById(int id, Account updatedAccount) {
        Account account = getAccountById(id);
        account.setEmail(updatedAccount.getEmail());
        account.setFirstName(updatedAccount.getFirstName());
        account.setLastName(updatedAccount.getLastName());
        account.setPassword(updatedAccount.getPassword());

        accRepo.save(account);
    }

    @Override
    public void updateAccountByEmail(String email, Account updatedAccount) {
        Account account = getAccountByEmail(email);
        account.setEmail(updatedAccount.getEmail());
        account.setFirstName(updatedAccount.getFirstName());
        account.setLastName(updatedAccount.getLastName());
        account.setPassword(updatedAccount.getPassword());

        accRepo.save(account);
    }

    @Override
    public void deleteAccountById(int id) {
        accRepo.deleteById(id);
    }
}
