package com.example.databaseTest.RestControllers;

import com.example.databaseTest.Entities.Portfolio;
import com.example.databaseTest.Entities.Transaction;
import com.example.databaseTest.Services.PortfolioService;
import com.example.databaseTest.Services.TransactionService;
import com.example.databaseTest.Entities.TransactionActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class TradeRestController {
    @Autowired
    private PortfolioService portService;

    @Autowired
    private TransactionService tranService;


//    @PostMapping(value = "/portfolio", consumes = {"application/json", "application/xml"})
//    public Portfolio addPortfolio(@RequestBody Portfolio portfolio){
//        portService.addNewPortfolio(portfolio);
//        return portfolio;
//    }

    @GetMapping(value = "/portfolio/{idType}")
    @ResponseBody
    public ResponseEntity<Portfolio> getPortfolioById(@PathVariable("idType") String idType, @RequestParam int id) {

        if(idType.equals("id")){
            Portfolio portfolio = portService.getPortfolioById(id);
            if (portfolio == null)
                return ResponseEntity.notFound().build();
            else
                return ResponseEntity.ok().body(portfolio);
        }
        else if(idType.equals("accId")){
            Portfolio portfolio = portService.getPortfolioByAccId(id);
            if (portfolio == null)
                return ResponseEntity.notFound().build();
            else
                return ResponseEntity.ok().body(portfolio);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/holdings")
    @ResponseBody
    public ResponseEntity<Map<String, Integer>> getHoldingsByAccId(@RequestParam int accId){
        Map<String, Integer> holdings = portService.getStockHoldingsByAccId(accId);
        if (holdings == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(holdings);
    }


//    @PutMapping(value = "/portfolio", consumes = {"application/json", "application/xml"})
//    public ResponseEntity<String> updatePortfolioByAccId(@RequestBody PortfolioDTO portfolioDto){
//        portService.updatePortfolioHoldings(portfolioDto);
//        return new ResponseEntity<String>("Update by ID Successful", HttpStatus.OK);
//
//    }

//    @PutMapping(value = "/portfolio/{idType}")
//    public ResponseEntity<String> updatePortfolioHoldings(@PathVariable("idType") String idType ,@RequestParam int id, String ticker, int quantity, double cash ){
//
//        if(idType.equals("id")) {
//
//            portService.updatePortfolioHoldingsById(id, ticker, quantity, cash);
//            return new ResponseEntity<String>("Update by ID Successful", HttpStatus.OK);
//        }
//        else if(idType.equals("accId")){
//
//            portService.updatePortfolioHoldingsByAccId(id, ticker, quantity, cash);
//            return new ResponseEntity<String>("Update by accID Successful", HttpStatus.OK);
//        }
//        return new ResponseEntity<String>("Invalid Id Type", HttpStatus.BAD_REQUEST);
//    }

    @PutMapping(value = "/portfolio/cash/{idType}")
    public ResponseEntity<String> updateCashHoldings(@PathVariable("idType") String idType ,@RequestParam int id, double cash ){
        if(idType.equals("id")) {
            portService.updatedCashHoldingsById(id, cash);
            return new ResponseEntity<String>("Update by ID Successful", HttpStatus.OK);
        }
        else if(idType.equals("accId")){
            portService.updatedCashHoldingsByAccId(id, cash);
            return new ResponseEntity<String>("Update by accID Successful", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalid Id Type", HttpStatus.BAD_REQUEST);

    }
    @PutMapping(value = "/portfolio/stock/{idType}")
    public ResponseEntity<String> updateStockHoldings(@PathVariable("idType") String idType ,@RequestParam int id, String ticker, int quantity){
        if(idType.equals("id")) {
            portService.updatedStockHoldingsById(id, ticker, quantity);
            return new ResponseEntity<String>("Update by ID Successful", HttpStatus.OK);
        }
        else if(idType.equals("accId")){
            portService.updatedStockHoldingsByAccId(id, ticker, quantity);
            return new ResponseEntity<String>("Update by accID Successful", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Invalid Id Type", HttpStatus.BAD_REQUEST);

    }

    @DeleteMapping(value = "/portfolio")
    @ResponseBody
    public ResponseEntity<String> deletePortfolioById(@RequestParam int id) {
        portService.deletePortfolioById(id);
        return new ResponseEntity<String>("DELETE by ID Successful", HttpStatus.OK);

    }


    @PostMapping(value = "/transaction", consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> addTransaction(@RequestBody Transaction transaction){
        TransactionActionType actionType = transaction.getActionType();

        if(actionType.toString().equals("BUY")){
//            return new ResponseEntity<String>("Actiontype is buy.", HttpStatus.OK);
            double cashHolding = portService.getCashHoldingsByAccId(transaction.getAccId());
            System.out.println(transaction.getExecPrice()*transaction.getQuantity());
            System.out.println(cashHolding);
            if(transaction.getExecPrice()*transaction.getQuantity() > cashHolding){
                return new ResponseEntity<String>("Insufficient Cash in account", HttpStatus.BAD_REQUEST);
            }
            else{
//                transaction.setStatus("PENDING");
                tranService.addNewTrx(transaction);
//                portService.updatePortfolioHoldingsByAccId(transaction.getAccId(), transaction.getTicker(), transaction.getQuantity(), transaction.getExecPrice()*-1*transaction.getQuantity());
                return new ResponseEntity<String>("Buy order is successfully placed.", HttpStatus.OK);

            }

        }
        else if(actionType.toString().equals("SELL")){
//            return new ResponseEntity<String>("Actiontype is sell.", HttpStatus.OK);

            // Add exception if stock or holdings object does not exist yet
            try {
                Map<String, Integer> stockHoldings = portService.getStockHoldingsByAccId(transaction.getAccId());
                if(stockHoldings.containsKey(transaction.getTicker())) {
                    if (transaction.getQuantity() > stockHoldings.get(transaction.getTicker())) {
                        return new ResponseEntity<String>("Insufficient stocks in account", HttpStatus.BAD_REQUEST);
                    } else {
//                        transaction.setStatus("PENDING");
                        tranService.addNewTrx(transaction);
//                        portService.updatePortfolioHoldingsByAccId(transaction.getAccId(), transaction.getTicker(), transaction.getQuantity()*-1, transaction.getExecPrice()*transaction.getQuantity());

                        return new ResponseEntity<String>("Sell order is successfully placed.", HttpStatus.OK);
                    }
                }
                else{
                    return new ResponseEntity<String>("Insufficient stocks in account", HttpStatus.OK);
                }
            }
            catch (Exception e){

                return new ResponseEntity<String>("Holdings does not exist yet.", HttpStatus.BAD_REQUEST);
            }

        }
        return new ResponseEntity<String>("Invalid action type.", HttpStatus.BAD_REQUEST);


    }
    @GetMapping(value = "/transaction")
    @ResponseBody
    public ResponseEntity<Transaction> getTransactionById(@RequestParam int id) {
        Transaction transaction = tranService.getTrxById(id);
        if (transaction == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(transaction);
    }

    @GetMapping(value = "/transactions")
    @ResponseBody
    public ResponseEntity<List<Transaction>> getAllTransactionsByAccId(@RequestParam int accId) {
        List<Transaction> transactions = tranService.getAllTrxByAccId(accId);
        if (transactions == null)
            return ResponseEntity.notFound().build();
        else
            return ResponseEntity.ok().body(transactions);
    }

    @PutMapping(value = "/transaction", consumes = {"application/json", "application/xml"})
    public ResponseEntity<String> updateTransactionById(@RequestParam int id, @RequestBody Transaction transaction){
        tranService.updateTrxStatusById(id, transaction);
        return new ResponseEntity<String>("Update by ID Successful", HttpStatus.OK);
    }

    @DeleteMapping(value = "/transaction")
    @ResponseBody
    public ResponseEntity<String> deleteTransactionById(@RequestParam int id) {
        tranService.deleteTrxById(id);
        return new ResponseEntity<String>("DELETE by ID Successful", HttpStatus.OK);

    }


}
