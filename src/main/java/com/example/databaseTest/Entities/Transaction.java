package com.example.databaseTest.Entities;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int accId;

    private String ticker;

    private int quantity;
    private double execPrice;
    private TransactionStatus status = TransactionStatus.CREATED;
    private TransactionActionType actionType;

//    @Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private String trxDatetime = java.time.LocalDateTime.now().toString();
    public Transaction(){}

    public Transaction(int accId, String ticker, int quantity){
        this.accId = accId;
        this.ticker = ticker;
        this.quantity = quantity;
    }
//    public Transaction(Account account, String ticker, int quantity, double indiPrice) {
//        this.account = account;
//        this.ticker = ticker;
//        this.quantity = quantity;
//        this.indiPrice = indiPrice;
//    }

    public int getAccId() {
        return accId;
    }

    public TransactionActionType getActionType() {
        return actionType;
    }

    public int getId() {
        return id;
    }

//    public Account getAccount() {
//        return account;
//    }

    public void setExecPrice(double execPrice) {
        this.execPrice = execPrice;
    }

    public String getTicker() {
        return ticker;
    }

//    public void setTicker(String ticker) {
//        this.ticker = ticker;
//    }

    public int getQuantity() {
        return quantity;
    }

//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }

    public double getExecPrice() {
        return execPrice;
    }

//    public void setExecPrice(double indiPrice) {
//        this.execPrice = execPrice;
//    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public String getTrxDatetime() {
        return trxDatetime;
    }

//    public void setTrxDatetime(Timestamp trxDatetime) {
//        this.trxDatetime = trxDatetime;
//    }

    public void setTrxDatetime(String trxDatetime) {
        this.trxDatetime = trxDatetime;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", ticker='" + ticker + '\'' +
                ", quantity=" + quantity +
                ", execPrice=" + execPrice +
                ", status='" + status + '\'' +
                ", trxDatetime=" + trxDatetime +
                '}';
    }
}