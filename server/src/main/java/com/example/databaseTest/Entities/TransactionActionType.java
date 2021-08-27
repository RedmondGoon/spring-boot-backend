package com.example.databaseTest.Entities;

public enum TransactionActionType {
    BUY("BUY"),
    SELL("SELL");

    private String transactionActionType;

    TransactionActionType(String transactionActionType) {
        this.transactionActionType = transactionActionType;
    }

    public String getTransactionActionType() {
        return this.transactionActionType;
    }
}
