package com.example.databaseTest.DatabaseIdentity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(CompositeKeyPortfolio.class)
public class PortFolio {

    @Id
    private Integer userid;
    @Id
    private String ticker;
    private Double priceBought;
    private java.sql.Timestamp timestamp;
}
