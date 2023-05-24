

public enum MonoCurrency {
    USD(840),
    EUR(978),
    UAH(980);

    private final int code;

    MonoCurrency(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static MonoCurrency fromCode(int code) {
        for (MonoCurrency currency : MonoCurrency.values()) {
            if (currency.getCode() == code) {
                return currency;
            }
        }
        throw new IllegalArgumentException("Invalid currency code: " + code);
    }
}