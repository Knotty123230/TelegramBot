package User;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Whiter {
    public static FileWriter white(Long chatId, String callbackData) throws IOException {
        List<User> info = UserInfo.getInfo(chatId, callbackData);
        String s = new Gson()
                .newBuilder()
                .setPrettyPrinting()
                .create()
                .toJson(info);
        String fileName = "info.users/info " + "chatId" + " " + chatId.toString() + ".json";
        FileWriter fileWriter = new FileWriter(fileName);
        fileWriter.write(s);
        fileWriter.flush();
        fileWriter.close();
        return fileWriter;
    }
}
