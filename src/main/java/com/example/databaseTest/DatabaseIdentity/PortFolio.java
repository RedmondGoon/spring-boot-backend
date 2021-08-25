package com.example.databaseTest.DatabaseIdentity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.sql.Timestamp;

@Entity
@IdClass(CompositeKeyPortfolio.class)
public class PortFolio {

    @Id
    private Integer userid;
    @Id
    private String ticker;
    private int volume;
    private java.sql.Timestamp timestamp;

    public int getVolume() {
        return volume;
    }


    public void setVolume(int volume) {
        this.volume = volume;
    }

    public Integer getUserid() {
        return userid;
    }

    public String getTicker() {
        return ticker;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
