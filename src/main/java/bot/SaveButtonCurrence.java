package bot;

import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class SaveButtonCurrence {
    public static String getButton(Update update){
        StringBuilder stringBuilder = new StringBuilder();
        InlineKeyboardMarkup replyMarkup = update.getCallbackQuery().getMessage().getReplyMarkup();
        List<List<InlineKeyboardButton>> keyboard = replyMarkup.getKeyboard();
        for (List<InlineKeyboardButton> inlineKeyboardButtons : keyboard) {
            for (InlineKeyboardButton inlineKeyboardButton : inlineKeyboardButtons) {
                if (inlineKeyboardButton.getCallbackData().contains("✅")) {
                    stringBuilder.append(inlineKeyboardButton.getText());
                }
            }
        }
        return stringBuilder.toString();
    }
}
