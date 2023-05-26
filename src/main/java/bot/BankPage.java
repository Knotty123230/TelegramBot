package bot;

import button.service.ButtonService;
import constants.PageLabels;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import service.BotService;
import service.Update;
import java.util.List;

public class BankPage implements Update {
    @Override
    public SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update) {
        List<String> nameOfButtons = new java.util.ArrayList<>(List.of(
                "НБУ",
                "ПриватБанк",
                "Монобанк",
                "OK"));
        List<String> callback = List.of(
                PageLabels.bankNBULabel,
                PageLabels.bankPrivatLabel,
                PageLabels.bankMonoLabel,
                "OK");

        SendMessage sendMessage = null;
        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            for (int i = 0; i < callback.size(); i++) {
                if (callbackData.equals(callback.get(i))) {
                    nameOfButtons.set(i, nameOfButtons.get(i) + "✅");
                    InlineKeyboardMarkup inlineKeyboardMarkup = button.service.ButtonService.sendButtonMessage(nameOfButtons, callback);
                    sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),
                            "Обери банк:");
                    sendMessage.setReplyMarkup(inlineKeyboardMarkup);


                }
            }
            if (update.getCallbackQuery().getData().equals(PageLabels.banksLabel)) {
                InlineKeyboardMarkup inlineKeyboardMarkup = ButtonService.sendButtonMessage(nameOfButtons, callback);
                sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(), "Обери банк:");
                sendMessage.setReplyMarkup(inlineKeyboardMarkup);
            }
        }
        return sendMessage;
    }
}

