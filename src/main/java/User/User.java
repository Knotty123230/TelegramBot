package User;

import lombok.Getter;
import lombok.Setter;

public class User {

    private long chatid;
    private String callbackDataSince;

    public User(long chatid, String callbackQuery) {
        this.chatid = chatid;
        this.callbackDataSince = callbackQuery;
    }

    public long getChatid() {
        return chatid;
    }

    public String getCallbackDataSince() {
        return callbackDataSince;
    }

    @Override
    public String toString() {
        return "User{" +
                "chatid=" + chatid +
                ", callbackQuery='" + callbackDataSince + '\'' +
                '}';
    }
}
