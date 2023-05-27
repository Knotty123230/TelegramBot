package currency.test;

import currency.Currency;
import currency.CurrencyService;

import currency.inpl.NBUCurrencyImpl;

public class NBUCurrencyImplTest {
    public static void main(String[] args) {
        CurrencyService currencyService = new NBUCurrencyImpl();
        double cr = currencyService.getCurrenceRate(Currency.EUR);
        System.out.println(cr);
    }
}
