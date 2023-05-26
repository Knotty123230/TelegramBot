package bot;

import User.User;
import button.service.ButtonService;
import constants.PageLabels;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CurrenceEditPage {
    private static final Set<String> selectedButtons = new HashSet<>();

    public static EditMessageReplyMarkup getUpdate(Update update) {
        List<String> nameOfButtons = new ArrayList<>(List.of(
                "USD",
                "EUR",
                "OK"
        ));
        List<String> callback = List.of(
                PageLabels.currUsdLabel,
                PageLabels.currEurLabel,
                "OK"
        );

        EditMessageReplyMarkup editMarkup = null;
        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            if (callbackData.equals(PageLabels.currUsdLabel) || callbackData.equals(PageLabels.currEurLabel)){
                update.getCallbackQuery();
            }
        }


        if (update.getCallbackQuery() != null) {
            editMarkup = new EditMessageReplyMarkup();
            editMarkup.setChatId(update.getCallbackQuery().getMessage().getChatId());
            editMarkup.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
            editMarkup.setReplyMarkup(ButtonService.sendButtonMessage(nameOfButtons, callback));
            return editMarkup;
        }

        return editMarkup;
    }

}