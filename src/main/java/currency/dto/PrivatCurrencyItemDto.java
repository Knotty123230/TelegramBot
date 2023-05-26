package currency.dto;

import currency.Currency;


public class PrivatCurrencyItemDto {
    private Currency ccy;
    private Currency base_ccy;
    private float buy;
    private float sale;

    public Currency getCcy() {
        return ccy;
    }

    public Currency getBase_ccy() {
        return base_ccy;
    }

    public float getBuy() {
        return buy;
    }

    public float getSale() {
        return sale;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "PrivatCurrencyItemDto{" +
                "ccy=" + ccy +
                ", base_ccy=" + base_ccy +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
    }
}