package bot;

import button.service.ButtonService;
import button.service.SaveButton;
import constants.PageLabels;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.BotService;
import java.util.ArrayList;
import java.util.List;

import static constants.PageLabels.*;
import static service.BotService.sendPhoto;

public class TelegramBot extends TelegramLongPollingBot {


    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private String username;
    private final List<String> buttonsCurrency = new ArrayList<>(List.of(
            "USD ✅",
            "EUR",
            "OK"
    ));
    private final List<String> buttonsSins = new ArrayList<>(List.of(
            "1 ✅",
            "2",
            "3",
            "4"));
    private final List<String> buttonsBank = new ArrayList<>(List.of(
            "НБУ",
            "ПриватБанк ✅",
            "Монобанк",
            "OK"));
    private final List<String> queryCurrency = new ArrayList<>(List.of(
            currUsdLabel + " ✅",
            currEurLabel,
            "OK"
    ));
    private final List<String> queryBank = new ArrayList<>(List.of(
            PageLabels.bankNBULabel,
            PageLabels.bankPrivatLabel,
            PageLabels.bankMonoLabel,
            "OK"));
    private final List<String> querySins = new ArrayList<>(List.of(
            "1",
            "2",
            "3",
            "4"));


    public TelegramBot(String botToken, String token, String username) {
        super(botToken);
        this.token = token;
        this.username = username;
    }


    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            if (update.getMessage().getText().equals("/start")) {
                if (update.hasMessage()) {
                    if (update.getMessage().getText().equals("/start")) {
                        try {
                            execute(new StartMessage().getUpdate(update));
                            SendPhoto sendPhoto = sendPhoto(update.getMessage().getChatId(), "photo/photo.jpg");
                            sendPhoto.setReplyMarkup(ButtonService.sendButtonMessage(List.of("Start"), List.of("Start")));
                            sendPhoto.setReplyMarkup(ButtonService.sendButtonMessage(List.of("Отримати інфо", "Налаштування"),
                                    List.of("Отримати інфо", "Налаштування")));
                            execute(sendPhoto);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        } else if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            switch (data) {
                case "Отримати інфо" -> {
                        try {
                            execute(new MessageWithSave().getUpdate(buttonsSins, buttonsBank, buttonsCurrency, update));
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }

                  default -> throw new RuntimeException();

                case "Налаштування", "start" -> {
                    try {
                        execute(new UserSettingsPage().getUpdate(update));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case currenciesLabel, currUsdLabel,currEurLabel,currUsdLabel + " ✅",currEurLabel + " ✅"   -> {
                        if (data.equals(currenciesLabel)){
                            try {
                            execute(CurrencyPage.editMessage(buttonsCurrency, queryCurrency, update));
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }else {
                            try {
                                execute(CurrenceEditPage.getUpdate(buttonsCurrency, queryCurrency, update));
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }

                case "OK" -> {
                    try {
                        execute(new OkButton().getUpdate(update));
                        execute(BotService.deleteMessage(update.getCallbackQuery().getMessage().getChatId().toString(),
                                update.getCallbackQuery().getMessage().getMessageId()));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case PageLabels.banksLabel -> {
                    try {
                        execute(new BankPage().getUpdate(buttonsBank, queryBank, update));
                        execute(BotService.deleteMessage(chatId.toString(), messageId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case PageLabels.bankNBULabel, PageLabels.bankPrivatLabel, PageLabels.bankMonoLabel -> {
                    try {
                        execute(new PageEdit().getUpdate(buttonsBank, queryBank, update));
                        execute(BotService.deleteMessage(chatId.toString(), messageId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }

                case PageLabels.commaSignsLabel -> {
                    try {
                        execute(new CountSince().getUpdate(buttonsSins, querySins, update));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }

                case "1", "2", "3", "4" -> {
                    try {
                        execute(new PageEdit().getUpdate(buttonsSins, querySins, update));
                        execute(BotService.deleteMessage(chatId.toString(), messageId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
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
