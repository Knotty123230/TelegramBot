package bot;

import button.service.ButtonService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import service.BotService;
import service.Update;

import java.util.List;

public class Count implements Update {

    @Override
    public SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {
        List<String> nameOfButtons = List.of("Отримати інфо",
                "Налаштування");
        List<String> callback = List.of("Отримати інфо",
                "Налаштування");
        System.out.println(nameOfButtons);
        System.out.println(callback);

        InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(callback, nameOfButtons);
        SendMessage sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Ласкаво просимо. Цей бот допоможе відстежувати актуальні курси валют");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }

}
