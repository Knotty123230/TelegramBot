package buttons;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static bot.TelegramBot.sendMessage;

public class Buttons {
    public static SendMessage createKeyboard(long id){
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        List<InlineKeyboardButton> button = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText("Банк");
        inlineKeyboardButton.setCallbackData("/setbank");
        button.add(inlineKeyboardButton);
        buttons.add(button);
        inlineKeyboardMarkup.setKeyboard(buttons);
        SendMessage message = sendMessage(id, "Обери банк:");
        message.setReplyMarkup(inlineKeyboardMarkup);
        return message;
    }
}
