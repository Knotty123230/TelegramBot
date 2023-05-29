package bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import service.Update;

import java.util.List;

import static service.BotService.sendMessage;

public class NotificationTimePage implements Update {


    @Override
    public SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {

        SendMessage sendMessage = sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери годину, о котрій щоденно отримуватимеш актуальний курс валют:");


        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setResizeKeyboard(true);

        KeyboardRow row1 = new KeyboardRow();
        row1.add(new KeyboardButton("9:00"));
        row1.add(new KeyboardButton("10:00"));
        row1.add(new KeyboardButton("11:00"));
        row1.add(new KeyboardButton("12:00"));


        KeyboardRow row2 = new KeyboardRow();
        row2.add(new KeyboardButton("13:00"));
        row2.add(new KeyboardButton("14:00"));
        row2.add(new KeyboardButton("15:00"));
        row2.add(new KeyboardButton("16:00"));

        // Створення третього рядка кнопок
        KeyboardRow row3 = new KeyboardRow();
        row3.add(new KeyboardButton("17:00"));
        row3.add(new KeyboardButton("18:00"));
        row3.add(new KeyboardButton("23:49"));
        row3.add(new KeyboardButton("23:50"));
        row3.add(new KeyboardButton("18:00"));
        row3.add(new KeyboardButton("Вимкнути повідомлення"));

        keyboardMarkup.setKeyboard(List.of(row1, row2, row3));


        sendMessage.setReplyMarkup(keyboardMarkup);


        return sendMessage;
    }


}



