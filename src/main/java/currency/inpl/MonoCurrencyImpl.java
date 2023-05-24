import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MonoCurrencyImpl implements CurrencyService {
    @Override
    public double getCurrenceRate(MonoCurrency currency) {
        String url = "https://api.monobank.ua/bank/currency";

        String json = "";

        try{
            json = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Type type = TypeToken.getParameterized(List.class, MonoCurrencyItemDto.class)
                .getType();
        List<MonoCurrencyItemDto> items = new Gson().fromJson(json,type);
        return items.stream()
                .filter(it -> it.getCurrencyCodeA() == currency)
                .filter(it -> it.getCurrencyCodeB() == MonoCurrency.UAH)
                .map(it->it.getRateBuy())
                .findFirst()
                .orElseThrow();

    }


}