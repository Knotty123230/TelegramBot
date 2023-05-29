package bot;

import service.ButtonService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import service.BotService;

import java.util.List;

public class BankPage {
    public SendMessage getUpdate(List<String> nameOfButtons, List<String> callback, Update update) {
        SendMessage sendMessage = null;
        InlineKeyboardMarkup inlineKeyboardMarkup;


        inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameOfButtons, callback);
        sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери банк:");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return sendMessage;
    }
}



