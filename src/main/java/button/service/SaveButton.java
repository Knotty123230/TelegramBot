package button.service;

import User.User;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.List;
import User.UserInfo;


public class SaveButton {
    public static String getSave(Update update) {
        StringBuilder callbackData = new StringBuilder();
        List<User> info = UserInfo.getInfo(update.getCallbackQuery().getMessage().getChatId(), update.getCallbackQuery().getData());
        for (User user : info) {
            long userChatid = user.getChatid();
            String callbackDataSince = user.getCallbackDataSince();
            if (update.getCallbackQuery().getMessage().getChatId().equals(userChatid)) {
                if (update.getCallbackQuery().getData().equals(callbackDataSince)) {
                    callbackData.append(callbackDataSince + " ");
                }
            }

        }
        return callbackData.toString();
    }
}
