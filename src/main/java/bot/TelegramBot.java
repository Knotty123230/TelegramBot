package bot;
import buttons.service.ButtonsService;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import static service.BotService.sendMessage;
import static service.BotService.sendPhoto;


public class TelegramBot extends TelegramLongPollingBot {


    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private String username;

    public TelegramBot(String botToken, String token, String username) {
        super(botToken);
        this.token = token;
        this.username = username;
    }


    @Override
    public void onUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        String inputText = update.getMessage().getText();
        if (inputText.startsWith("/start")) {
            try {
                execute(sendMessage(chatId, "Привіт, я бот, який надає актуальні курси валют!"));
                execute(sendPhoto(chatId, "photo/photo.jpg"));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

            }else if (inputText.equals("settings")){
            SendMessage message = sendMessage(chatId, "Налаштування");
            message.setReplyMarkup(ButtonsService.getKeyboard("Settings", "set"));
            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        }
    public  void botConnect() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(this);
    }

    @Override
    public String getBotUsername() {
        return username;
    }
}
