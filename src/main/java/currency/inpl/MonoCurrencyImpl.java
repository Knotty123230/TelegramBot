package currency.inpl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import currency.Currency;
import currency.CurrencyService;
import currency.dto.MonoCurrencyItemDto;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class MonoCurrencyImpl implements CurrencyService {

    @Override
    public double getCurrenceRate(Currency currency) {
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
                .filter(it -> it.getCurrencyCodeB() == Currency.UAH)
                .map(MonoCurrencyItemDto::getRateBuy)
                .findFirst()
                .orElseThrow();

    }
    @Override
    public double getCurrenceRateSell(Currency currency) {
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
                .filter(it -> it.getCurrencyCodeB() == Currency.UAH)
                .map(MonoCurrencyItemDto::getRateSell)
                .findFirst()
                .orElseThrow();

    }


}