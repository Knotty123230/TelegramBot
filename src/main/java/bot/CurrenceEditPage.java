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
                for (int i = 0; i < inlineKeyboardButtons.size(); i++) {
                    if (inlineKeyboardButtons.get(i).getCallbackData().equals(update.getCallbackQuery().getData())) {
                        if (inlineKeyboardButtons.get(i).getCallbackData().contains("✅")) {
                            inlineKeyboardButtons.get(i).setText(inlineKeyboardButtons.get(i).getText().replace(" ✅", ""));
                            inlineKeyboardButtons.get(i).setCallbackData(inlineKeyboardButtons.get(i).getCallbackData().replace(" ✅", ""));
                        } else {
                            inlineKeyboardButtons.get(i).setText(inlineKeyboardButtons.get(i).getText() + " ✅");
                            inlineKeyboardButtons.get(i).setCallbackData(inlineKeyboardButtons.get(i).getCallbackData() + " ✅");

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