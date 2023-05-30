package bot;

import service.ButtonService;
import constants.PageLabels;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import service.BotService;

import java.util.List;


public class CurrencyPage {
    public static SendMessage editMessage(List<String> nameOfButtons, List<String> callback, Update update) {
        SendMessage sendMessage = null;
        if (update.getCallbackQuery().getData().equals(PageLabels.currenciesLabel)) {
            InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameOfButtons, callback);
            sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери валюту:");
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }


        return sendMessage;
    }
}