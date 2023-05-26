
public class MonoCurrencyImplTest {
    public static void main(String[] args) {
        CurrencyService currencyService = new MonoCurrencyImpl();

        try {
            double rateEUR = currencyService.getCurrenceRate(MonoCurrency.EUR);
            System.out.println("EUR to UAH rate: " + rateEUR);

            double rateUSD = currencyService.getCurrenceRate(MonoCurrency.USD);
            System.out.println("USD to UAH rate: " + rateUSD);

            double rateUAH = currencyService.getCurrenceRate(MonoCurrency.UAH);
            System.out.println("UAH to UAH rate: " + rateUAH);
        } catch (Exception e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }
}