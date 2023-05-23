
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class PrivatCurrencyImpl implements CurrencyService {
    @Override
    public double getCurrenceRate(Currency currency) {
        String url ="https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=11"
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
        Type type = TypeToken.getParameterized(List.class, PrivatCurrencyItemDto.class)
                .getType();
        List<PrivatCurrencyItemDto> items = new Gson().fromJson(json,type);
        return items.stream()
                .filter(it -> it.getCcy() == currency)
                .filter(it -> it.getBase_ccy == Currency.UAH)
                .map(it-> it.getBuy())
                .findFirst()
                .orElseThrow();
    }


}