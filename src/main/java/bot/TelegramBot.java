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

        String save = "";
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
        }else if (update.hasCallbackQuery()) {
                String data = update.getCallbackQuery().getData();
                Long chatId = update.getCallbackQuery().getMessage().getChatId();
                Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
                switch (data) {
                    default -> {
                        try {
                            execute(CurrenceEditPage.getUpdate(update));
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    case "Налаштування", "start" -> {
                        try {
                            execute(new UserSettingsPage().getUpdate(update));
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }


                    case PageLabels.currenciesLabel -> {
                        try {
                            execute(CurrencyPage.getUpdate(update));
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    case "OK" -> {
                        try {
                            execute(new OkButton().getUpdate(update));
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case PageLabels.banksLabel, PageLabels.bankNBULabel, PageLabels.bankPrivatLabel, PageLabels.bankMonoLabel -> {
                        try {
                            execute(new BankPage().getUpdate(update));
                            execute(BotService.deleteMessage(chatId.toString(), messageId));
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    case PageLabels.commaSignsLabel -> {
                        try {
                            execute(new CountSince().getUpdate(update));
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                        save = SaveButton.getSave(update);
                    }

                    case "1", "2", "3", "4" -> {
                        try {
                            execute(new CountSince().getUpdate(update));
                            save = SaveButton.getSave(update);
                            execute(BotService.deleteMessage(chatId.toString(), messageId));
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }


            }
        }


        public void botConnect () throws TelegramApiException {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
        }


        @Override
        public String getBotUsername () {
            return username;
        }


    }
