package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private Long cbu;
    private Double sum;
    private String type;

    public Transaction() {
    }

    public Transaction(Long cbu, Double sum, String type) {
        this.cbu = cbu;
        this.sum = sum;
        this.type = type;
    }
    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long target_cbu) {
        this.cbu = target_cbu;
    }
    public String getType() {
        return type;
    }

    public Double getSum() {
        return sum;
    }
}
