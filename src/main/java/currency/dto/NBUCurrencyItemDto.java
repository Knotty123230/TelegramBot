
import lombok.Data;

@Data
public class NBUCurrencyItemDto {
    private Currency cc;
    private Float rate;

    @Override
    public String toString() {
        return "Currency{" +
                "cc=" + cc +
                ", rate=" + rate +
                '}';
    }
}
