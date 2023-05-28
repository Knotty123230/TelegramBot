package bot;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Map;

public class MapPuts {
    public static void getKeys(Update update, List<String> list, Map<Long, List<String>> hashmap) {
        Long chatId = update.getCallbackQuery().getMessage().getChatId();

        if (!hashmap.containsKey(chatId)) {
            hashmap.put(chatId, list);
        }
        }
    }

