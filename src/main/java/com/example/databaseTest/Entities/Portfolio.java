package com.example.databaseTest.Entities;


import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "Portfolio")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "account_id")
    @OneToOne()
    @JoinColumn(name = "accId")
    private Account account;


//    @JoinColumn(name = "account_id")


//    @ElementCollection
//    @MapKeyColumn(name="ticker")
//    @Column(name="quantity")
//    @CollectionTable(name="holdings", joinColumns=@JoinColumn(name="id"))

//    @OneToMany(mappedBy = "portfolio")
//    @MapKeyColumn(name="ticker")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Holdings", joinColumns = @JoinColumn(name = "portId"))
    @MapKeyColumn(name = "ticker")
    @Column(name = "quantity")
    private Map<String, Integer> holdings;

    private double cashBalance;

    public Portfolio(){}

    public Portfolio(Map<String, Integer> holdings, double cashBalance) {
//        this.account = account;
        this.holdings = holdings;
        this.cashBalance = cashBalance;
    }

    public Map<String, Integer> getHoldings() {
        return holdings;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void setHoldings(Map<String, Integer> holdings) {
        this.holdings = holdings;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
