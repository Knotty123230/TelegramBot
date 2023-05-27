package currency;

public enum Currency {

    UAH(980),
    USD(840),
    EUR(978);

    private final int code;

    Currency(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static Currency fromCode(int code) {
        for (Currency currency : Currency.values()) {
            if (currency.getCode() == code) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Invalid currency code: " + code);
    }
}