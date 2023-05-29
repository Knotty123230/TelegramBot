package bot;

import constants.PageLabels;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import service.BotService;
import service.Update;
import uIElements.buttons.ButtonService;

import java.util.List;

public class NotificationTimePage implements Update {
    @Override
    public SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {

        SendMessage sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери годину, о котрій щоденно отримуватимеш актуальний курс валют:");


        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true); // Масштабувати клавіатуру автоматично

        // Створення першого рядка кнопок
        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("9:00"));
        row1.add(new KeyboardButton("10:00"));
        row1.add(new KeyboardButton("11:00"));
        row1.add(new KeyboardButton("12:00"));


        // Створення другого рядка кнопок
        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("13:00"));
        row2.add(new KeyboardButton("14:00"));
        row2.add(new KeyboardButton("15:00"));
        row2.add(new KeyboardButton("16:00"));

        // Створення третього рядка кнопок
        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton("17:00"));
        row3.add(new KeyboardButton("18:00"));
        row3.add(new KeyboardButton("Вимкнути повідомлення"));

        // Додавання рядків до клавіатури
        keyboardMarkup.setKeyboard(List.of(row1, row2, row3));


        sendMessage.setReplyMarkup(keyboardMarkup);


        return sendMessage;
    }
}
