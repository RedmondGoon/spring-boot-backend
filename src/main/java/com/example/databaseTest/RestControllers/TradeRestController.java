 package com.example.databaseTest.RestControllers;

import com.example.databaseTest.Entities.Portfolio;
import com.example.databaseTest.Entities.Transaction;
import com.example.databaseTest.ExternalAPI.AlphaVantage;
import com.example.databaseTest.Services.NewsService;
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

    @Autowired
    private NewsService newsService;


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

    @GetMapping(value = "/holdings/stocks")
    @ResponseBody
    public ResponseEntity<List<List<String>>> getStockHoldingsByAccId(@RequestParam int accId) {
        List<List<String>> holdings = portService.getStockBreakdownByAccId(accId);
        if (holdings == null)
            return ResponseEntity.notFound().build();
        else {
            return ResponseEntity.ok().body(holdings);
        }
    }

    @GetMapping(value = "/holdings/{type}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getTotalHoldingsByAccId(@PathVariable("type") String type, @RequestParam int accId){
        return ResponseEntity.ok().body(portService.getPortfolioValueByAccId(accId, type));
    }

    @GetMapping(value = "/chart")
    @ResponseBody
    public ResponseEntity<List<Map<String, String>>> getChart(int accId){
        return ResponseEntity.ok().body(portService.getChartByAccId(accId));
    }

    @GetMapping(value = "/news")
    @ResponseBody
    public ResponseEntity<List<String>> getHeadlines(){
        return ResponseEntity.ok().body(newsService.getLatestHeadlines());
    }

    @PutMapping(value = "/portfolio/cash/{idType}")
    public ResponseEntity<String> updateCashHoldings(@PathVariable("idType") String idType ,@RequestParam int id, double cash ){
        if(idType.equals("id")) {
            if(portService.updateCashHoldingsById(id, cash)){
                return new ResponseEntity<String>("Update by accID Successful", HttpStatus.OK);

            }
            else{
                return new ResponseEntity<String>("Insufficient cash in account", HttpStatus.BAD_REQUEST);

            }
        }
        else if(idType.equals("accId")){
            if(portService.updateCashHoldingsByAccId(id, cash)){
                return new ResponseEntity<String>("Update by accID Successful", HttpStatus.OK);

            }
            else{
                return new ResponseEntity<String>("Insufficient cash in account", HttpStatus.BAD_REQUEST);

            }
        }
        return new ResponseEntity<String>("Invalid Id Type", HttpStatus.BAD_REQUEST);

    }
    @PutMapping(value = "/portfolio/stock/{idType}")
    public ResponseEntity<String> updateStockHoldings(@PathVariable("idType") String idType ,@RequestParam int id, String ticker, int quantity){
        if(idType.equals("id")) {
            portService.updateStockHoldingsById(id, ticker, quantity);
            return new ResponseEntity<String>("Update by ID Successful", HttpStatus.OK);
        }
        else if(idType.equals("accId")){
            portService.updateStockHoldingsByAccId(id, ticker, quantity);
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
        transaction.setExecPrice(AlphaVantage.getStockQuoteAWS(transaction.getTicker()));

        if(actionType.toString().equals("BUY")){
            double cashHolding = portService.getCashHoldingsByAccId(transaction.getAccId());
            if(transaction.getExecPrice()*transaction.getQuantity() > cashHolding){
                return new ResponseEntity<String>("Insufficient cash in account", HttpStatus.BAD_REQUEST);
            }
            else{
                tranService.addNewTrx(transaction);
                portService.updateCashHoldingsByAccId(transaction.getAccId(), transaction.getExecPrice()*-1*transaction.getQuantity());
                return new ResponseEntity<String>("Buy order is successfully placed.", HttpStatus.OK);
            }
        }
        else if(actionType.toString().equals("SELL")){

            try {
                Map<String, Integer> stockHoldings = portService.getStockHoldingsByAccId(transaction.getAccId());
                if(stockHoldings.containsKey(transaction.getTicker())) {
                    if (transaction.getQuantity() > stockHoldings.get(transaction.getTicker())) {
                        return new ResponseEntity<String>("Insufficient stocks in account", HttpStatus.BAD_REQUEST);
                    } else {
                        tranService.addNewTrx(transaction);
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
