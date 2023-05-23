package bot;
import constants.PageLabels;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uIElements.buttons.CurrencyBotButton;
import uIElements.messages.CurrencyBotMessage;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
                execute(CurrencyBotMessage.createMessage(chatId, "Привіт, я бот, який надає актуальні курси валют!"));
                execute(sendPhoto(chatId, "photo/photo.jpg"));
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }

            }


        /* Page "User Settings" and its buttons  - start */

        if(update.hasCallbackQuery()) {

            if(update.getCallbackQuery().getData().equals(PageLabels.userSettingsLabel)) {
                SendMessage message = CurrencyBotMessage.createMessage(chatId, "Налаштування: ");


                List<String> buttons = Arrays.asList(
                        "Кількість знаків після коми",
                        "Банк",
                        "Валюти",
                        "Час оповіщень"
                );

                CurrencyBotButton.attachButtons(message, Map.of(
                                buttons.get(0), PageLabels.commaSignsLabel,
                                buttons.get(1), PageLabels.banksLabel,
                                buttons.get(2), PageLabels.currenciesLabel,
                                buttons.get(3), PageLabels.timeLabel
                                ));
                sendApiMethodAsync(message);
            }
        }

        /* Page "User Settings" and its buttons  - END */







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
