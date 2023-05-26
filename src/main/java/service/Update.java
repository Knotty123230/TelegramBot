package service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;


public interface Update {
    SendMessage getUpdate(org.telegram.telegrambots.meta.api.objects.Update update);
}
