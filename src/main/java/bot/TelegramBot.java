package bot;

import User.UserSettings;
import button.service.ButtonService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.PageLabels.*;
import static service.BotService.sendPhoto;

public class TelegramBot extends TelegramLongPollingBot {


    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private String username;
    private final Map<Long, UserSettings> userSettingsMap = new HashMap<>();






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

            UserSettings userSettings = userSettingsMap.get(chatId);
            if (userSettings == null) {
                userSettings = new UserSettings();
                userSettingsMap.put(chatId, userSettings);
            }

            switch (data) {
                case "Отримати інфо" -> {
                        try {
                            execute(new MessageWithSave().getUpdate(userSettings.getButtonsSins(), userSettings.getButtonsBank(),
                                    userSettings.getButtonsCurrency(), update));
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
                            execute(CurrencyPage.editMessage(userSettings.getButtonsCurrency(), userSettings.getQueryCurrency(), update));
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }else {
                            try {
                                execute(CurrenceEditPage.getUpdate(userSettings.getButtonsCurrency(), userSettings.getQueryCurrency(), update));
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
                        execute(new BankPage().getUpdate(userSettings.getButtonsBank(), userSettings.getQueryBank(), update));
                        execute(BotService.deleteMessage(chatId.toString(), messageId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case PageLabels.bankNBULabel, PageLabels.bankPrivatLabel, PageLabels.bankMonoLabel -> {
                    try {
                        execute(new PageEdit().getUpdate(userSettings.getButtonsBank(), userSettings.getQueryBank(), update));
                        execute(BotService.deleteMessage(chatId.toString(), messageId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }

                case PageLabels.commaSignsLabel -> {
                    try {
                        execute(new CountSince().getUpdate(userSettings.getButtonsSins(), userSettings.getQuerySins(), update));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }

                case "1", "2", "3", "4" -> {
                    try {
                        execute(new PageEdit().getUpdate(userSettings.getButtonsSins(),userSettings.getQuerySins(), update));
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
