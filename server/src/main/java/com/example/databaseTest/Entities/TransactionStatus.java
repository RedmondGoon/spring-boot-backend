package com.example.databaseTest.Entities;

public enum TransactionStatus {
    CREATED("CREATED"),
    PROCESSING("PROCESSING"),
    FILLED("FILLED"),
    REJECTED("REJECTED");

    private String status;

    private TransactionStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
