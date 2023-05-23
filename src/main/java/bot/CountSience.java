package bot;

import button.service.ButtonService;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import service.BotService;
import service.Update;
import java.util.List;

public class CountSience implements Update {

    @Override
    public SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {
        List<String> nameOfButtons = List.of("1",
                "2",
                "3",
                "4");
        List<String> callback = List.of("1",
                "2",
                "3",
                "4");
        System.out.println(nameOfButtons);
        System.out.println(callback);

        InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameOfButtons, callback);
        SendMessage sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери к-сть знаків після коми");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
