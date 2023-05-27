package currency.test;

import currency.Currency;

import currency.inpl.MonoCurrencyImpl;

public class MonoCurrencyImplTest {
    public static void main(String[] args) {
        MonoCurrencyImpl monoCurrency = new MonoCurrencyImpl();

        try {
            double rateEUR = monoCurrency.getCurrenceRate(Currency.EUR);
            double sellEUR = monoCurrency.getCurrenceRateSell(Currency.EUR);
            System.out.println("EUR to UAH rate: " + rateEUR + " EUR to UAH sell:  " + sellEUR );

//            double rateUSD = monoCurrency.getCurrenceRate(MonoCurrency.USD);
//            double sellUSD = monoCurrency.getCurrenceRateSell(MonoCurrency.USD);
//            System.out.println("USD to UAH rate: " + rateUSD + " USD to UAH sell:  " + sellUSD );


        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}