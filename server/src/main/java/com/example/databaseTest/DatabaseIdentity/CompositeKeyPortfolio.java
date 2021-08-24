package com.example.databaseTest.DatabaseIdentity;

import javax.persistence.Id;
import java.io.Serializable;

public class CompositeKeyPortfolio implements Serializable {
    @Id
    private Integer userid;
    @Id
    private String ticker;

}
