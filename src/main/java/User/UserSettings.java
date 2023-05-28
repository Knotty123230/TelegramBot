package User;

import constants.PageLabels;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static constants.PageLabels.currEurLabel;
import static constants.PageLabels.currUsdLabel;

public class UserSettings {

    private final List<String> buttonsCurrency= new ArrayList<>(List.of(
            "USD ✅",
            "EUR",
            "OK"
    ));
    private final List<String> buttonsBank= new ArrayList<>(List.of(
            "НБУ",
            "ПриватБанк ✅",
            "Монобанк",
            "OK"));
    private final List<String> buttonsSins = new ArrayList<>(List.of(
            "1 ✅",
            "2",
            "3",
            "4"));
    private final List<String> queryCurrency = new ArrayList<>(List.of(
            currUsdLabel + " ✅",
            currEurLabel,
            "OK"
    ));
    private final List<String> queryBank= new ArrayList<>(List.of(
            PageLabels.bankNBULabel,
            PageLabels.bankPrivatLabel,
            PageLabels.bankMonoLabel,
            "OK"));
    private final List<String> querySins = new ArrayList<>(List.of(
            "1",
            "2",
            "3",
            "4"));

    public List<String> getButtonsCurrency() {
        return buttonsCurrency;
    }

    public List<String> getButtonsBank() {
        return buttonsBank;
    }

    public List<String> getButtonsSins() {
        return buttonsSins;
    }

    public List<String> getQueryCurrency() {
        return queryCurrency;
    }

    public List<String> getQueryBank() {
        return queryBank;
    }

    public List<String> getQuerySins() {
        return querySins;
    }
}