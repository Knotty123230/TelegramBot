package parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.ArrayList;

public class BankParser<T> {
    public ArrayList<T> getValue(String url){
        try {
            String text = Jsoup.connect(url).ignoreContentType(true).get().text();
            return new Gson().fromJson(text, new TypeToken<ArrayList<T>>() {
            }.getType());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
