package bot;

import button.service.ButtonService;
import constants.PageLabels;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


import java.util.List;

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

                try {
                    execute(new StartMessage().getUpdate(update));
                    SendPhoto sendPhoto = sendPhoto(update.getMessage().getChatId(), "photo/photo.jpg");
                    sendPhoto.setReplyMarkup(ButtonService.sendButtonMessage(List.of("Start"), List.of("Start")));
                    execute(sendPhoto);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }

                    throw new RuntimeException(e);
            }
        }

        /*  to open Settings to choose time for notification */
        if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            if (data.equals(PageLabels.timeLabel)) {
                try {
                    execute(new NotificationTimePage().getUpdate(update));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void botConnect() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(this);
    }


    @Override
    public String getBotUsername() {
        return username;
    }
}
