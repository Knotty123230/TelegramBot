package bot;

import button.service.ButtonService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import service.BotService;
import service.Update;

import java.util.List;

public class СountOfSiens implements Update {

    @Override
    public SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {
        SendMessage sendMessage = null;
        if (update.hasCallbackQuery()){
            String data = update.getCallbackQuery().getData();
            if (data.equals("Start")){
                InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(List.of("1", "2", "3", "4"), List.of("1", "2", "3", "4"));
                sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери к-сть знаків після коми");
                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            }
        }
        return sendMessage;
    }
}
