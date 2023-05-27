package currency.test;

import currency.Currency;


import currency.inpl.PrivatCurrencyImpl;

public class PrivatCurrencyImplTest {
    public static void main(String[] args) {
        PrivatCurrencyImpl currencyService = new PrivatCurrencyImpl();
        double cr = currencyService.getCurrenceRate(Currency.EUR);
        double sell = currencyService.getCurrenceRateSell(Currency.EUR);
        System.out.println("EUR to UAH rate: " + cr + " EUR to UAH sell:  "+ sell);
    }

}
