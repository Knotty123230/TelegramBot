package bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import service.Update;

public class OkButton implements Update {

    @Override
    public SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {
        if (update.hasCallbackQuery()) {
            if (update.getCallbackQuery().getData().equals("OK")) {
                return new UserSettingsPage().getUpdate(update);
            }
        }
        return null;
    }
}
