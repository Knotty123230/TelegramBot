package User;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {


    public static List<User> getInfo(Long chatId, String callbackQuery) {
        User user = new User(chatId, callbackQuery);
        List<User> users = new ArrayList<>();
        users.add(user);
        System.out.println(users);
        return users;
    }
}
