package User;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    @Setter
    private long chatid;
    @Getter
    @Setter
    private final String callbackDataSince;

    public User(long chatid, String callbackQuery) {
        this.chatid = chatid;
        this.callbackDataSince = callbackQuery;
    }

    @Override
    public String toString() {
        return "User{" +
                "chatid=" + chatid +
                ", callbackQuery='" + callbackDataSince + '\'' +
                '}';
    }
}
