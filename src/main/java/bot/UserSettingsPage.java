package bot;

import constants.PageLabels;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import service.BotService;
import service.Update;
import uIElements.buttons.ButtonService;

import java.util.List;

public class UserSettingsPage implements Update {
    @Override
    public SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {
        List<String> nameOfButtons = List.of(
                "Кількість знаків після коми",
                        "Банк",
                        "Валюти",
                        "Час оповіщень");
        List<String> callback = List.of(
                    PageLabels.commaSignsLabel,
                    PageLabels.banksLabel,
                    PageLabels.currenciesLabel,
                    PageLabels.timeLabel);

        InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameOfButtons, callback);
        SendMessage sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери налаштування:");
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        return sendMessage;
    }
}

///* Page "User Settings" and its buttons  - start */
//
//        if(update.hasCallbackQuery()) {
//
//            if(update.getCallbackQuery().getData().equals(PageLabels.userSettingsLabel)) {
//                SendMessage message = CurrencyBotMessage.createMessage(chatId, "Налаштування: ");
//
//
//                List<String> buttons = Arrays.asList(
//                        "Кількість знаків після коми",
//                        "Банк",
//                        "Валюти",
//                        "Час оповіщень"
//                );
//
//                CurrencyBotButton.attachButtons(message, Map.of(
//                                buttons.get(0), PageLabels.commaSignsLabel,
//                                buttons.get(1), PageLabels.banksLabel,
//                                buttons.get(2), PageLabels.currenciesLabel,
//                                buttons.get(3), PageLabels.timeLabel
//                                ));
//                sendApiMethodAsync(message);
//            }
//        }
//
//        /* Page "User Settings" and its buttons  - END */
