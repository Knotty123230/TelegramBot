package bot;

import button.service.ButtonService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import service.BotService;
import service.Update;

import java.util.ArrayList;
import java.util.List;

public class CountSience implements Update {

    @Override
    public SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {
        SendMessage sendMessage = null;
        List<String> nameOfButtons = new ArrayList<>(List.of("1",
                "2",
                "3",
                "4"));
        List<String> callback = List.of("1",
                "2",
                "3",
                "4");
        String callbackData = update.getCallbackQuery().getData();
            for (int i = 0; i < callback.size(); i++) {
                if (callbackData.equals(callback.get(i))) {
                    nameOfButtons.set(i, nameOfButtons.get(i) + "\u2705");
                    InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameOfButtons, callback);
                    sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),
                            "Обери к-сть знаків після коми");
                    sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                    return sendMessage;
                }
            }
        if (update.hasCallbackQuery()){
            InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameOfButtons, callback);
            sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери к-сть знаків після коми");
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            return sendMessage;
        }
        return sendMessage;
    }
}
