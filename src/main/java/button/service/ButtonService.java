package button.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

public class ButtonService {
    public static InlineKeyboardMarkup sendButtonMessage(List<String> nameButton, List<String> callbackData) {

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        if (nameButton.size() == 0 && callbackData.size() == 0) {
            inlineKeyboardButton.setText(nameButton.get(0));
            inlineKeyboardButton.setCallbackData(callbackData.get(0));
            row.add(inlineKeyboardButton);
            keyboard.add(row);
            keyboardMarkup.setKeyboard(keyboard);
        } else {
            for (int i = 0; i < nameButton.size(); i++) {
                inlineKeyboardButton = new InlineKeyboardButton();
                inlineKeyboardButton.setText(nameButton.get(i));
                inlineKeyboardButton.setCallbackData(callbackData.get(i));
                row = new ArrayList<>();
                row.add(inlineKeyboardButton);
                keyboard.add(row);
            }
            keyboardMarkup.setKeyboard(keyboard);
        }
        return keyboardMarkup;
    }
}
