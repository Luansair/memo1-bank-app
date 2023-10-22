package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cbu;

    private Double balance;
    private Double promo_profit;

    public Account(){
    }

    public Account(Double balance) {
        this.balance = balance;
        this.promo_profit = 0.0;
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void setPromoProfit(Double profit) {
        promo_profit = profit;
    }

    public Double getPromoProfit() {
        return promo_profit;
    }

}
