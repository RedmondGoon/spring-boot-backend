package com.example.databaseTest.DatabaseIdentity;


import com.example.databaseTest.ActionType;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer transactionId;

//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name ="user", referencedColumnName = "id")
    private  Integer userID ;

//    alternativer @CreationTimestamp
//  @@UpdateTimestamp
    @Column(name="timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private java.sql.Timestamp transactionDatetime;

    private String ticker;

    private String actionType;

    private Integer volume;

    private Double PriceAtTime;

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }


    public String getActionType() {
        return actionType;
    }



    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public void setTransactionDatetime(Timestamp transactionDatetime) {
        this.transactionDatetime = transactionDatetime;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setPriceAtTime(Double priceAtTime) {
        PriceAtTime = priceAtTime;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public Integer getUserID() {
        return userID;
    }

    public Timestamp getTransactionDatetime() {
        return transactionDatetime;
    }

    public String getTicker() {
        return ticker;
    }

    public Integer getVolume() {
        return volume;
    }

    public Double getPriceAtTime() {
        return PriceAtTime;
    }


}
