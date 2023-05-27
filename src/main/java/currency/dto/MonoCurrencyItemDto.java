package currency.dto;

import currency.Currency;

public class MonoCurrencyItemDto {
    private int currencyCodeA ;
    private int currencyCodeB ;
    private float date;
    private float rateBuy;
    private float rateCross;
    private float rateSell;


    public Currency getCurrencyCodeA() {
        return Currency.fromCode(currencyCodeA);
    }



    public Currency getCurrencyCodeB() {
        return Currency.fromCode(currencyCodeB);
    }

    public void setCurrencyCodeA(int currencyCodeA) {
        this.currencyCodeA = currencyCodeA;
    }

    public float getDate() {
        return date;
    }

    public void setDate(float date) {
        this.date = date;
    }

    public float getRateBuy() {
        return rateBuy;
    }

    public void setRateBuy(float rateBuy) {
        this.rateBuy = rateBuy;
    }

    public float getRateCross() {
        return rateCross;
    }

    public void setRateCross(float rateCross) {
        this.rateCross = rateCross;
    }

    public float getRateSell() {
        return rateSell;
    }

    public void setRateSell(float rateSell) {
        this.rateSell = rateSell;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "MonoCurrencyItemDto{" +
                "currencyCodeA=" + currencyCodeA +
                ", currencyCodeB=" + currencyCodeB +
                ", date=" + date +
                ", rateBuy=" + rateBuy +
                ", rateCross=" + rateCross +
                ", rateSell=" + rateSell +
                '}';
    }
}
