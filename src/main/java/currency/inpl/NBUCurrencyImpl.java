

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.test.Currency;
import org.example.test.CurrencyItemDto;
import org.example.test.CurrencyService;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class NBUCurrencyImpl implements CurrencyService {
    @Override
    public double getCurrenceRate(Currency currency) {
        String url = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchangenew?json";
        String json = null;
        try{
            json = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Type type = TypeToken.getParameterized(List.class, NBUCurrencyItemDto.class)
                .getType();
        List<NBUCurrencyItemDto> items = new Gson().fromJson(json,type);
        return items.stream()
                .filter(it -> it.getCc() == currency)
                .map(it-> it.getRate())
                .findFirst()
                .orElseThrow();
    }


}
