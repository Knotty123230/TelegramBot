package bot;

import constants.PageLabels;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import service.BotService;
import service.Update;
import uIElements.buttons.ButtonService;

import java.util.*;

public class CurrencyPage implements Update {
        @Override
        public SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {
            List<String> nameOfButtons = new ArrayList<>(List.of(
                    "USD",
                    "EUR",
                    "OK"));
            List<String> callback = List.of(
                    PageLabels.currUsdLabel,
                    PageLabels.currEurLabel,
                    "OK"
            );
            System.out.println(nameOfButtons);
            System.out.println(callback);
            SendMessage sendMessage = null;

            for (int i = 0; i < nameOfButtons.size(); i++) {
                if (update.getCallbackQuery().getData().equals(callback.get(i))) {
                    String buttonText = nameOfButtons.get(i);
                    if (buttonText.endsWith(" ✅")) {
                        nameOfButtons.set(i, buttonText.substring(0, buttonText.length() - 2));
                    } else {
                        nameOfButtons.set(i, buttonText + " ✅");
                        sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери валюту (можеш вибрати декілька):");
                        InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameOfButtons, callback);
                        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                    }
                }
            }

            InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameOfButtons, callback);
            sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери валюту (можеш вибрати декілька):");
            sendMessage.setReplyMarkup(inlineKeyboardMarkup);

            return sendMessage;
        }
    }
