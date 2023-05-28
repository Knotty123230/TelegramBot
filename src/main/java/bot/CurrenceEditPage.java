package bot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;


@Slf4j

public class CurrenceEditPage {

    public static EditMessageReplyMarkup getUpdate(List<String> buttonList, List<String> callbackList, Update update) {
        EditMessageReplyMarkup editMarkup = new EditMessageReplyMarkup();
        editMarkup.setChatId(update.getCallbackQuery().getMessage().getChatId());
        editMarkup.setMessageId(update.getCallbackQuery().getMessage().getMessageId());

        InlineKeyboardMarkup replyMarkup = update.getCallbackQuery().getMessage().getReplyMarkup();
        List<List<InlineKeyboardButton>> keyboard = replyMarkup.getKeyboard();

        for (List<InlineKeyboardButton> row : keyboard) {
            for (InlineKeyboardButton button : row) {
                if (button.getCallbackData().equals(update.getCallbackQuery().getData())) {
                    int buttonIndex = keyboard.indexOf(row);
                    String buttonText = buttonList.get(buttonIndex);
                    String buttonCallback = callbackList.get(buttonIndex);

                    if (buttonText.contains(" ✅")) {
                        buttonText = buttonText.replace(" ✅", "");
                        buttonCallback = buttonCallback.replace(" ✅", "");
                    } else {
                        buttonText = buttonText + " ✅";
                        buttonCallback = buttonCallback + " ✅";
                    }

                    button.setText(buttonText);
                    button.setCallbackData(buttonCallback);
                    buttonList.set(buttonIndex, buttonText);
                    callbackList.set(buttonIndex, buttonCallback);
                }
            }
        }

        replyMarkup.setKeyboard(keyboard);
        editMarkup.setReplyMarkup(replyMarkup);

        return editMarkup;
    }
}