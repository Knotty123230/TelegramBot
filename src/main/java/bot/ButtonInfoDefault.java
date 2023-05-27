package bot;

import currency.Currency;
import currency.inpl.PrivatCurrencyImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import service.BotService;
import service.Update;

import java.text.DecimalFormat;

public class ButtonInfoDefault implements Update {

    @Override
    public SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {
        double exchangeRate = new PrivatCurrencyImpl().getCurrenceRate(Currency.USD);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String formattedRate = decimalFormat.format(exchangeRate);
        return BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),
                "Курс долара до гривні\n" +
                        "Покупка: " + formattedRate);
    }
}
