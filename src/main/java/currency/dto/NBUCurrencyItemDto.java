package currency.dto;

import currency.Currency;

public class NBUCurrencyItemDto {
    private Currency cc;
    private float rate;

    public Currency getCc() {
        return cc;
    }

    public float getRate() {
        return rate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "cc=" + cc +
                ", rate=" + rate +
                '}';
    }
}
