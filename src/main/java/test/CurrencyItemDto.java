package org.example.test;

import lombok.Data;

@Data
public class CurrencyItemDto {
    private Currency cc;

    public Currency getCc() {
        return cc;
    }

    public void setCc(Currency cc) {
        this.cc = cc;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    private Float rate;

    @Override
    public String toString() {
        return "Currency{" +
                "ccy=" + cc +
                ", rate=" + rate +
                '}';
    }
}
