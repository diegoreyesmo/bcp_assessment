package com.coralogix.calculator.entities;

//import lombok.Data;
//import lombok.ToString;

import javax.persistence.*;

@Entity
//@Data
//@ToString
public class ExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String originCurrency;
    private String finalCurrency;
    private String date;
    private Double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginCurrency() {
        return originCurrency;
    }

    public void setOriginCurrency(String originCurrency) {
        this.originCurrency = originCurrency;
    }

    public String getFinalCurrency() {
        return finalCurrency;
    }

    public void setFinalCurrency(String finalCurrency) {
        this.finalCurrency = finalCurrency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "id=" + id +
                ", originCurrency='" + originCurrency + '\'' +
                ", finalCurrency='" + finalCurrency + '\'' +
                ", date='" + date + '\'' +
                ", value=" + value +
                '}';
    }
}
