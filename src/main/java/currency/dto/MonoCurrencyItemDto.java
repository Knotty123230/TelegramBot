
import lombok.Data;
@Data
public class MonoCurrencyItemDto {
    private MonoCurrency currencyCodeA ;
    private MonoCurrency currencyCodeB ;
    private float date;
    private float rateBuy;
    private float rateCross;
    private float rateSell;


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