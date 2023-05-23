package bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import service.Update;

public class StartMessage implements Update {

    @Override
    public SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {
        SendMessage sendMessage = null;
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            if (text.equals("/start")) {
                sendMessage = new SendMessage();
                sendMessage.setChatId(chatId);
                sendMessage.setText("Привіт, я бот, який надає актуальні курси валют!");
            }
        }
        return sendMessage;
    }
}
