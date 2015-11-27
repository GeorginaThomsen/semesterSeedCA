/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.currencyDailyService;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author terziev
 */
@Entity
public class DailyExchangeRate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(cascade={CascadeType.PERSIST})
    private Currency currency;
    
    private String date_;
    
    private String rate;

    public DailyExchangeRate() {
    }

    public DailyExchangeRate(Currency currency, String date_, String rate) {
        this.currency = currency;
        this.date_ = date_;
        this.rate = rate;
    }

    public String getDate_() {
        return date_;
    }

    public void setDate_(String date_) {
        this.date_ = date_;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "DailyExchangeRate{" + "id=" + id + ", currency=" + currency + ", date_=" + date_ + ", rate=" + rate + '}';
    }

 

    
    
}
