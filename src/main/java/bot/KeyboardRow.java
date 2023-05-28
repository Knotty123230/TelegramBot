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

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow keyboardRow =
                new org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow();
        keyboardRow.addAll(List.of("9","10","11"));
        org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow keyboardRow1 =
                new org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow();
        keyboardRow1.addAll(List.of("12","13","14"));
        org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow keyboardRow2 =
                new org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow();
        keyboardRow1.addAll(List.of("15","16","17"));
        org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow keyboardRow3 =
                new org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow();
        keyboardRow3.addAll(List.of("18","Вимкнути повідомлення"));
        List<org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow> row = new ArrayList<>();
        row.add(keyboardRow);
        row.add(keyboardRow1);
        row.add(keyboardRow2);
        row.add(keyboardRow3);


        replyKeyboardMarkup.setKeyboard(row);
        SendMessage sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),"Виберіть час сповіщень");
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        return sendMessage;
    }
}
