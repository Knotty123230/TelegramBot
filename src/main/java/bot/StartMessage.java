package bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import service.Update;

public class StartMessage implements Update {

    @Override
    public  SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText("Привіт, я бот, який надає актуальні курси валют!");
        return sendMessage;
    }
}
