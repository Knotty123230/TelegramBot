package User;

import org.telegram.telegrambots.meta.api.objects.Update;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Reader {
    public static String read(Update update) throws FileNotFoundException {
        String inLine = "";
        String fileName = "info.users/info " + "chatId" + " " + update.getCallbackQuery().getMessage().getChatId().toString() + ".json";
        Scanner scanner = new Scanner(fileName);
        if (scanner.hasNext()){
           inLine = scanner.findInLine(update.getCallbackQuery().getData());
        }
        return inLine;
    }
}
