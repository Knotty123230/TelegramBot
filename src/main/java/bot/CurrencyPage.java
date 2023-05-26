package bot;
import User.User;
import User.UserInfo;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
public class CurrencyPage {
    private static final Set<String> selectedCurrencies = new HashSet<>();
    public static EditMessageReplyMarkup getUpdate(Update update) {
        List<String> currencies = List.of("USD", "EUR");
        SendMessage sendMessage;
        List<User> info = null;
        EditMessageReplyMarkup editMarkup = null;
        if (update.hasCallbackQuery()) {
            String callbackData = update.getCallbackQuery().getData();
            if (currencies.contains(callbackData)) {
                if (selectedCurrencies.contains(callbackData)) {
                    selectedCurrencies.remove(callbackData);
                } else {
                    selectedCurrencies.add(callbackData);
                }
                info = UserInfo.getInfo(update.getCallbackQuery().getMessage().getChatId(), callbackData);
            }
        }
        String selectedCurrenciesText = selectedCurrencies.stream()
                .collect(Collectors.joining(", "));
        // Create buttons for currencies
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        for (String currency : currencies) {
            boolean isSelected = selectedCurrencies.contains(currency);
            String buttonText = isSelected ? currency + ":белая_галочка:" : currency;
            InlineKeyboardButton button = new InlineKeyboardButton(buttonText);
            button.setCallbackData(currency);
            List<InlineKeyboardButton> row = new ArrayList<>();
            row.add(button);
            keyboard.add(row);
        }
        // Add OK button
        InlineKeyboardButton okButton = new InlineKeyboardButton("OK");
        okButton.setCallbackData("OK");
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(okButton);
        keyboard.add(row);
        InlineKeyboardMarkup replyMarkup = new InlineKeyboardMarkup();
        replyMarkup.setKeyboard(keyboard);
        if (update.getCallbackQuery() != null) {
            editMarkup = new EditMessageReplyMarkup();
            editMarkup.setChatId(update.getCallbackQuery().getMessage().getChatId());
            editMarkup.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
            editMarkup.setReplyMarkup(replyMarkup);
        }
        String messageText = "Обери валюту (можеш вибрати декілька):\n\n" + selectedCurrenciesText;
        sendMessage = new SendMessage();
        sendMessage.setChatId(update.getCallbackQuery().getMessage().getChatId());
        sendMessage.setText(messageText);
        return editMarkup;
    }
}