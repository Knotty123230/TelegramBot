package bot;

import constants.PageLabels;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import service.BotService;
import service.ButtonService;
import service.Update;

import java.util.List;

public class UserSettingsPage implements Update {
    @Override
    public SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {
        List<String> nameOfButtons = List.of(
                "Кількість знаків після коми",
                "Банк",
                "Валюти",
                "Час оповіщень");
        List<String> callback = List.of(
                PageLabels.commaSignsLabel,
                PageLabels.banksLabel,
                PageLabels.currenciesLabel,
                PageLabels.timeLabel);

        InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameOfButtons, callback);
        SendMessage sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери налаштування:");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
