package bot;

import User.UserSettings;
import User.save.SaveUser;
import service.ButtonService;
import constants.PageLabels;
import it.sauronsoftware.cron4j.Scheduler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.BotService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static constants.PageLabels.*;
import static service.BotService.sendPhoto;

public class TelegramBot extends TelegramLongPollingBot {


    private final String token;
    private final String username;
    private Map<Long, UserSettings> userSettingsMap = new HashMap<>();
    private final Map<Long, UserSettings> notificationMap = new HashMap<>();
    private final Scheduler scheduler = new Scheduler();


    public TelegramBot(String botToken, String token, String username) {
        super(botToken);
        this.token = token;
        this.username = username;
        this.userSettingsMap = SaveUser.load("application.json");
    }


    @Override
    public void onUpdateReceived(Update update) {
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
            UserSettings userSettings = userSettingsMap.get(update.getMessage().getChatId());
            if (userSettings == null) {
                userSettings = new UserSettings();
                userSettingsMap.put(update.getMessage().getChatId(), userSettings);
            }

            userSettings.setNotificationTime(update.getMessage().getText());
            System.out.println(userSettings.getNotificationTime());
            switch (userSettings.getNotificationTime()) {
                case "9:00", "10:00",
                        "11:00", "12:00",
                        "13:00", "14:00",
                        "15:00", "16:00",
                        "17:00", "18:00" -> {
                    try {
                        execute(BotService.sendMessage(update.getMessage().getChatId(), "Вам буде надіслано актуальний курс валют, " +
                                "які ви обрали, об " + userSettings.getNotificationTime() + "!"));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                case "Вимкнути повідомлення" -> {
                    try {
                        execute(BotService.sendMessage(update.getMessage().getChatId(), "Ви вимкнули повідомлення!"));
                        userSettings.setNotificationsEnabled(false);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if (!userSettings.isSchedulerRunning() || !userSettings.getNotificationTime().equals(update.getMessage().getText())) {
                if (userSettings.isSchedulerRunning()) {
                    scheduler.stop();
                }

                scheduler.schedule("* * * * *", () -> sendScheduledMessages(update));
                scheduler.start();

                userSettings.setSchedulerRunning(true);
                userSettings.setNotificationTime(update.getMessage().getText());
            }


        } else if (update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            Long chatId = update.getCallbackQuery().getMessage().getChatId();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            UserSettings userSettings = userSettingsMap.get(update.getCallbackQuery().getMessage().getChatId());
            if (userSettings == null) {
                userSettings = new UserSettings();
                userSettingsMap.put(update.getCallbackQuery().getMessage().getChatId(), userSettings);
            }


            switch (data) {
                case "Назад⬆" -> {
                    try {
                        execute(BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),
                                "Привіт, я бот, який надає актуальні курси валют!"));
                        SendPhoto sendPhoto = sendPhoto(update.getCallbackQuery().getMessage().getChatId(), "photo/photo.jpg");
                        sendPhoto.setReplyMarkup(ButtonService.sendButtonMessage(List.of("Start"), List.of("Start")));
                        sendPhoto.setReplyMarkup(ButtonService.sendButtonMessage(List.of("Отримати інфо", "Налаштування"),
                                List.of("Отримати інфо", "Налаштування")));
                        execute(sendPhoto);
                        execute(BotService.deleteMessage(chatId.toString(), messageId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }

                case "Отримати інфо" -> {
                    try {
                        execute(new MessageWithSave().getUpdate(chatId, userSettings.getButtonsSins(), userSettings.getButtonsBank(),
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
                case currenciesLabel, currUsdLabel, currEurLabel, currUsdLabel + " ✅", currEurLabel + " ✅" -> {
                    if (data.equals(currenciesLabel)) {
                        try {
                            execute(CurrencyPage.editMessage(userSettings.getButtonsCurrency(), userSettings.getQueryCurrency(), update));
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
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

                case PageLabels.timeLabel -> {
                    try {
                        execute(new NotificationTimePage().getUpdate(update));
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
                        execute(new PageEdit().getUpdate(userSettings.getButtonsSins(), userSettings.getQuerySins(), update));
                        execute(BotService.deleteMessage(chatId.toString(), messageId));
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }
        SaveUser.save(userSettingsMap != null ? userSettingsMap : null, "application.json");
    }

    public void sendScheduledMessages(Update update) {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String currentTimeFormatted = currentTime.format(formatter);
        System.out.println(currentTimeFormatted);
        for (Map.Entry<Long, UserSettings> entry : userSettingsMap.entrySet()) {
            Long chatId = entry.getKey();
            UserSettings userSettings = entry.getValue();
            if (update.getMessage().getChatId().equals(chatId) && currentTimeFormatted.equals(userSettings.getNotificationTime())
                    && userSettings.isNotificationsEnabled()) {
                try {
                    System.out.println(userSettings);
                    SendMessage update1 = new MessageWithSave().getUpdate(chatId, userSettings.getButtonsSins(), userSettings.getButtonsBank(),
                            userSettings.getButtonsCurrency(), update);
                    execute(update1);
                    break;
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


    public Map<Long, UserSettings> getNotificationMap() {
        return notificationMap;
    }
}
