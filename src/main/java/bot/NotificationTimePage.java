package bot;

import constants.PageLabels;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import service.BotService;
import service.Update;
import uIElements.buttons.ButtonService;

import java.util.ArrayList;
import java.util.List;

public class NotificationTimePage implements Update {
    @Override
    public SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();

        List<KeyboardRow> nameOfButtons = List.of();

        KeyboardRow row = new KeyboardRow();

        row.add("9");
        row.add("10");
        row.add("11");

        nameOfButtons.add(row);

        row = new KeyboardRow();

        row.add("12");
        row.add("13");
        row.add("14");

        nameOfButtons.add(row);

        row = new KeyboardRow();

        row.add("15");
        row.add("16");
        row.add("17");

        nameOfButtons.add(row);

        row = new KeyboardRow();

        row.add("18");
        row.add("Вимкнути повідомлення");

        nameOfButtons.add(row);

        keyboardMarkup.setKeyboard(nameOfButtons);

        List<KeyboardRow> callback = List.of();

        row.add(PageLabels.notificationTime9);
        row.add(PageLabels.notificationTime10);
        row.add(PageLabels.notificationTime11);

        callback.add(row);

        row = new KeyboardRow();

        row.add(PageLabels.notificationTime12);
        row.add(PageLabels.notificationTime13);
        row.add(PageLabels.notificationTime14);

        callback.add(row);

        row = new KeyboardRow();

        row.add(PageLabels.notificationTime15);
        row.add(PageLabels.notificationTime16);
        row.add(PageLabels.notificationTime17);

        callback.add(row);

        row = new KeyboardRow();

        row.add(PageLabels.notificationTime18);
        row.add(PageLabels.notificationTimeOff);

        callback.add(row);

        keyboardMarkup.setKeyboard(callback);

        InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessageKeyboardRow(nameOfButtons, callback);
        SendMessage sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери годину, о котрій щоденно отримуватимеш актуальний курс валют:");
        sendMessage.setReplyMarkup(keyboardMarkup);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return sendMessage;
    }
}
