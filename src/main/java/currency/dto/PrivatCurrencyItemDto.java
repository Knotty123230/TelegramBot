
import lombok.Data;
@Data
public class PrivatCurrencyItemDto {
    private Currency ccy;
    private Currency base_ccy;
    private float buy;
    private float sale;

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