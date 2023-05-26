package User;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {


    public static List<User> getInfo(Long chatId, String callbackQuery) {
        List<User> users = new ArrayList<>();
        users.add(new User(chatId, callbackQuery));
        return users;
    }
}
