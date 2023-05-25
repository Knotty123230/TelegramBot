package uIElements.messages;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.nio.charset.StandardCharsets;

public class CurrencyBotMessage {
    String messageText;

    public static SendMessage createMessage(long chatId, String messageText) {
        SendMessage message = new SendMessage();
        message.setText(new String(messageText.getBytes(), StandardCharsets.UTF_8));
        message.setParseMode("markdown");
        message.setChatId(chatId);
        return message;
    }
}


