package currency.test;

import currency.MonoCurrency;
import currency.inpl.MonoCurrencyImpl;

public class MonoCurrencyImplTest {
    public static void main(String[] args) {
        MonoCurrencyImpl monoCurrency = new MonoCurrencyImpl();

        try {
            double rateEUR = monoCurrency.getCurrenceRate(MonoCurrency.EUR);
            System.out.println("EUR to UAH rate: " + rateEUR);

            double rateUSD = monoCurrency.getCurrenceRate(MonoCurrency.USD);
            System.out.println("USD to UAH rate: " + rateUSD);

            double rateUAH = monoCurrency.getCurrenceRate(MonoCurrency.UAH);
            System.out.println("UAH to UAH rate: " + rateUAH);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}