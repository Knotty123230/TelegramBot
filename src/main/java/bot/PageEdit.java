package bot;

import service.ButtonService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import service.BotService;

import java.util.List;

public class PageEdit {
    public SendMessage getUpdate(List<String> nameofButtons, List<String> callback, Update update) {
        SendMessage sendMessage = null;
        InlineKeyboardMarkup inlineKeyboardMarkup;
        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();

            for (int i = 0; i < callback.size(); i++) {
                if (callbackData.equals(callback.get(i))) {
                    if (!nameofButtons.get(i).contains("✅")) {
                        nameofButtons.set(i, nameofButtons.get(i) + " ✅");
                    }
                } else {
                    if (nameofButtons.get(i).contains("✅")) {
                        nameofButtons.set(i, nameofButtons.get(i).replace(" ✅", ""));
                    }
                }
            }

            inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameofButtons, callback);
            sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери варіант");
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }

        return sendMessage;
    }
}
