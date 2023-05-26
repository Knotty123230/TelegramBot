package bot;
import button.service.ButtonService;
import constants.PageLabels;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import service.BotService;

import java.util.ArrayList;
import java.util.List;

public class CurrencyPage {
    public static SendMessage getUpdate(Update update) {
        List<String> nameOfButtons = new ArrayList<>(List.of(
                "USD",
                "EUR",
                "OK"
        ));
        List<String> callback = List.of(
                PageLabels.currUsdLabel,
                PageLabels.currEurLabel,
                "OK"
        );
        SendMessage sendMessage = null;
        if (update.getCallbackQuery().getData().equals(PageLabels.currenciesLabel)) {
            InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameOfButtons, callback);
            sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери кнопки");
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }
        return sendMessage;
    }
}