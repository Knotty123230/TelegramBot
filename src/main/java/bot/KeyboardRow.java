package bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import service.Update;
import constants.PageLabels;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import service.BotService;
import service.Update;
import uIElements.buttons.ButtonService;

import java.util.ArrayList;
import java.util.List;

public class KeyboardRow {

    public static SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {


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


        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow keyboardRow =
                new org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow();
        keyboardRow.addAll(List.of(nameOfButtons.get(0),nameOfButtons.get(1),nameOfButtons.get(2)));
        org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow keyboardRow1 =
                new org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow();
        keyboardRow1.addAll(List.of(nameOfButtons.get(3),nameOfButtons.get(4),nameOfButtons.get(5)));
        org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow keyboardRow2 =
                new org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow();
        keyboardRow2.addAll(List.of(nameOfButtons.get(6),nameOfButtons.get(7),nameOfButtons.get(8)));
        org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow keyboardRow3 =
                new org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow();
        keyboardRow3.addAll(List.of(nameOfButtons.get(9),nameOfButtons.get(10)));
        List<org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow> row = new ArrayList<>();
        row.add(keyboardRow);
        row.add(keyboardRow1);
        row.add(keyboardRow2);
        row.add(keyboardRow3);


        replyKeyboardMarkup.setKeyboard(row);
        InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameOfButtons, callback);
        SendMessage sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),"Виберіть час сповіщень");
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
}
