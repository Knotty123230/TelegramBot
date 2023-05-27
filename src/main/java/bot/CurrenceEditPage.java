package bot;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.List;


@Slf4j

public class CurrenceEditPage {

    public static EditMessageReplyMarkup getUpdate(Update update) {

        EditMessageReplyMarkup editMarkup = null;
        if (update.hasCallbackQuery()) {
            InlineKeyboardMarkup replyMarkup = update.getCallbackQuery().getMessage().getReplyMarkup();
            List<List<InlineKeyboardButton>> keyboard = replyMarkup.getKeyboard();
            for (List<InlineKeyboardButton> inlineKeyboardButtons : keyboard) {
                for (InlineKeyboardButton inlineKeyboardButton : inlineKeyboardButtons) {
                    if (inlineKeyboardButton.getCallbackData().equals(update.getCallbackQuery().getData())) {
                        if (inlineKeyboardButton.getCallbackData().contains("✅")) {
                            inlineKeyboardButton.setText(inlineKeyboardButton.getText().replace(" ✅", ""));
                            inlineKeyboardButton.setCallbackData(inlineKeyboardButton.getCallbackData().replace(" ✅", ""));
                        } else {
                            inlineKeyboardButton.setText(inlineKeyboardButton.getText() + " ✅");
                            inlineKeyboardButton.setCallbackData(inlineKeyboardButton.getCallbackData() + " ✅");
                        }

                        replyMarkup.setKeyboard(keyboard);
                        editMarkup = new EditMessageReplyMarkup();
                        editMarkup.setChatId(update.getCallbackQuery().getMessage().getChatId());
                        editMarkup.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                        editMarkup.setReplyMarkup(replyMarkup);
                        return editMarkup;
                    }

                }
            }
        }


        return editMarkup;
    }


}