package service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import java.io.File;

public class BotService {
    public static SendPhoto sendPhoto(long id, String filePath){
        SendPhoto photo = new SendPhoto();
        photo.setChatId(id);
        photo.setPhoto(new InputFile(new File(filePath)));
        return photo;
    }

    public static SendMessage sendMessage(long id, String text){
        SendMessage message = new SendMessage();
        message.setChatId(id);
        message.setText(text);
        return message;
    }
    public static DeleteMessage deleteMessage(String chatId, Integer messageId){
        return new DeleteMessage(chatId, messageId);
    }
}
