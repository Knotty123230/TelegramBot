import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBot telegramBot = new TelegramBot("6286472591:AAHzm1f-Jhzb8pZLsKi9QLHJF3JGTJ4y5Sw",
                "6286472591:AAHzm1f-Jhzb8pZLsKi9QLHJF3JGTJ4y5Sw",
                "projectvaluesbot");
        telegramBot.botConnect();
    }
}
