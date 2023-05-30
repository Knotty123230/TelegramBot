package User.save;

import User.UserSettings;
import User.UserInfo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaveUser {

    public static void save(Map<Long, UserSettings> map, String fileName) {
        List<UserInfo.UserSettingsEntry> entries = new ArrayList<>();
        for (Map.Entry<Long, UserSettings> entry : map.entrySet()) {
            Long chatId = entry.getKey();
            UserSettings userSettings = entry.getValue();
            UserInfo.UserSettingsEntry userSettingsEntry = new UserInfo.UserSettingsEntry(chatId, userSettings);
            entries.add(userSettingsEntry);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(entries);

            try (FileWriter writer = new FileWriter(fileName)) {
                writer.write(json);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static Map<Long, UserSettings> load(String fileName) {
        Map<Long, UserSettings> map = new HashMap<>();

        File file = new File(fileName);
        if (!file.exists() || file.length() == 0) {
            return map;
        }

        try (FileReader reader = new FileReader(fileName)) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<UserInfo.UserSettingsEntry>>() {
            }.getType();
            List<UserInfo.UserSettingsEntry> entries = gson.fromJson(reader, type);

            for (UserInfo.UserSettingsEntry entry : entries) {
                Long chatId = entry.getChatId();
                UserSettings userSettings = entry.getUserSettings();
                map.put(chatId, userSettings);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
