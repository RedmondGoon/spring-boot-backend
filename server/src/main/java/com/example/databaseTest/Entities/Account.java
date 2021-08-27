package com.example.databaseTest.Entities;

import javax.persistence.*;

@Entity // This tells Hibernate to make a table out of this class
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

//    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
//    @JoinColumn(name = "accId")
    private Portfolio portfolio;


    public Account(){}
//    public Account(String password, String firstName, String lastName, String email) {
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.portfolio = new Portfolio(new HashMap<String, Integer>()
//                ,0);
//        this.portfolio.setAccount(this);
//
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        portfolio.setAccount(this);
        this.portfolio = portfolio;
    }


}