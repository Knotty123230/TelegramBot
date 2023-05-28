package bot;


import button.service.ButtonService;
import constants.PageLabels;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import service.BotService;
import java.util.List;

public class CountSince  {


    public SendMessage getUpdate(List<String> nameOfButtons, List <String> callback, Update update){
        SendMessage sendMessage = null;
        String callbackData = update.getCallbackQuery().getData();
            for (int i = 0; i < callback.size(); i++) {
                if (callbackData.equals(callback.get(i))) {
                    nameOfButtons.set(i, nameOfButtons.get(i) + "✅");
                    InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameOfButtons, callback);
                    sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),
                            "Обери к-сть знаків після коми");
                    sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                }
            }
        if (update.getCallbackQuery().getData().equals(PageLabels.commaSignsLabel)){
            InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameOfButtons, callback);
            sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери к-сть знаків після коми");
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        }
        return sendMessage;
    }
}

