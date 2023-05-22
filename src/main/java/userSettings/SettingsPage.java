package userSettings;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import userSettings.messages.CurrencyBotMessage;


import java.util.Map;

public class SettingsPage {

    Update update = new Update();
    long chatId;
    String messageText = "Налаштування:";

    

        if (update.hasCallbackQuery()) {
        if (update.getCallbackQuery().getData().equals("userSettingsPage")) { //як тут використати константу замість рядка?


            //відправти повідомлення
            SendMessage message = CurrencyBotMessage.createMessage(chatId, messageText);


            //і прикріпляємо кнопкy
            attachButtons(message, Map.of(
                    "Кількість знаків після коми", "commaSignsPage",
                    "Банк", "banksPage",
                    "Валюти", "currenciesPage",
                    "Час оповіщень", "timePage"

            ));
            sendApiMethodAsync(message);
        }
    }
}

