package com.example.databaseTest.RestControllers;


import com.example.databaseTest.Entities.Account;
import com.example.databaseTest.Services.AccountService;
import com.example.databaseTest.Services.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Map;

@RestController
public class RegistryRestController {

    @Autowired
    private AccountService accService;

    @PostMapping(value = "/account", consumes = {"application/json", "application/xml"})
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public ResponseEntity<String> registerAccount(@RequestBody Account account){
        accService.addNewAccount(account);
        return new ResponseEntity<String>("Account has been created", HttpStatus.OK);
    }
//    @GetMapping(value = "/account")
//    @ResponseBody
//    public ResponseEntity<Account> getAccountById(@RequestParam int id) {
//        Account result = accService.getAccountById(id);
//        if (result == null)
//            return ResponseEntity.notFound().build();
//        else
//            return ResponseEntity.ok().body(result);
//    }

    @GetMapping(value = "/account")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getAccountByEmailAndPassword(@RequestParam String email, String password) {
        Map<String, String> result = accService.getAccountDetailsByEmailAndPassword(email, password);
        if (result == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(result);
    }
    @PutMapping(value = "/account", consumes = {"application/json", "application/xml"})
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<String> updateAccountById(@RequestParam int id, @RequestBody Account account){
        accService.updateAccountById(id, account);
        return new ResponseEntity<String>("Update by ID Successful", HttpStatus.OK);
    }
    @DeleteMapping(value = "/account")
    @CrossOrigin(origins = "http://localhost:3000")
    @ResponseBody
    public ResponseEntity<String> deleteAccountById(@RequestParam int id) {
        accService.deleteAccountById(id);
        return new ResponseEntity<String>("DELETE by ID Successful", HttpStatus.OK);

    }



}
