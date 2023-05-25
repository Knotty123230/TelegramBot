package bot;

import constants.PageLabels;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import service.BotService;
import service.Update;
import uIElements.buttons.ButtonService;

import java.util.List;

public class NotificationTimePage implements Update {
    @Override
    public SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {
        List<String> nameOfButtons = List.of(
                "9",
                "10",
                "11",
                "12",
                "13",
                "14",
                "15",
                "16",
                "17",
                "18",
                "Вимкнути повідомлення");
        List<String> callback = List.of(
                PageLabels.notificationTime9,
                PageLabels.notificationTime10,
                PageLabels.notificationTime11,
                PageLabels.notificationTime12,
                PageLabels.notificationTime13,
                PageLabels.notificationTime14,
                PageLabels.notificationTime15,
                PageLabels.notificationTime16,
                PageLabels.notificationTime17,
                PageLabels.notificationTime18,
                PageLabels.notificationTimeOff);


        InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameOfButtons, callback);
        SendMessage sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери годину, о котрій щоденно отримуватимеш актуальний курс валют:");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}
