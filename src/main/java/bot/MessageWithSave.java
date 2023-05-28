package bot;

import currency.Currency;
import currency.MonoCurrency;
import currency.inpl.MonoCurrencyImpl;
import currency.inpl.NBUCurrencyImpl;
import currency.inpl.PrivatCurrencyImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.BotService;

import java.text.DecimalFormat;

public class MessageWithSave {

    public SendMessage getUpdate(String buttonSigns, String buttonBank, String buttonCurrency, Update update) throws RuntimeException {
        SendMessage sendMessage = null;
        NBUCurrencyImpl nbuCurrency;
        MonoCurrencyImpl monoCurrency;
        PrivatCurrencyImpl privatCurrency;
        DecimalFormat decimalFormat = null;
        String formattedRate;
        double currenceRate;
        System.out.println("я в методі");
        switch (buttonSigns) {
            case "1 " -> decimalFormat = new DecimalFormat("#.0");
            case "2 " -> decimalFormat = new DecimalFormat("#.00");
            case "3 " -> decimalFormat = new DecimalFormat("#.000");
            case "4 " -> decimalFormat = new DecimalFormat("#.0000");
        }

        switch (buttonBank) {
            case "Монобанк " -> {
                monoCurrency = new MonoCurrencyImpl();
                currenceRate = monoCurrency.getCurrenceRate(switch (buttonCurrency) {
                    case "USD" + " ✅":
                        yield MonoCurrency.USD;
                    case "EUR" + " ✅":
                        yield MonoCurrency.EUR;
                    default:
                        throw new RuntimeException();
                });
                formattedRate = decimalFormat != null ? decimalFormat.format(currenceRate) : null;
                System.out.println("я в моно");
                sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),
                        "Курс долара до гривні\n" +
                                "Покупка: " + formattedRate);
            }
            case "ПриватБанк " -> {
                privatCurrency = new PrivatCurrencyImpl();
                currenceRate = privatCurrency.getCurrenceRate(switch (buttonCurrency) {
                    case "USD ✅":
                        yield Currency.USD;
                    case "EUR ✅":
                        yield Currency.EUR;
                    default:
                        throw new RuntimeException();
                });
                formattedRate = decimalFormat != null ? decimalFormat.format(currenceRate) : null;
                sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),
                        "Курс долара до гривні\n" +
                                "Покупка: " + formattedRate);
            }
            case "НБУ " -> {
                nbuCurrency = new NBUCurrencyImpl();
                currenceRate = nbuCurrency.getCurrenceRate(switch (buttonCurrency) {
                    case "USD" + " ✅":
                        yield Currency.USD;
                    case "EUR" + " ✅":
                        yield Currency.EUR;
                    default:
                        throw new RuntimeException();
                });
                formattedRate = decimalFormat != null ? decimalFormat.format(currenceRate) : null;
                sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),
                        "Курс долара до гривні\n" +
                                "Покупка: " + formattedRate);
            }
        }
        return sendMessage;
    }
}
