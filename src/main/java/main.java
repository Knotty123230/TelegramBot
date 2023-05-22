import bankservices.PrivatBankService;
import bot.TelegramBot;
import constants.Constants;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class main {
    public static void main(String[] args) throws TelegramApiException {


        TelegramBot telegramBot = new TelegramBot(Constants.TOKEN,
                Constants.TOKEN,
                Constants.USERNAME);
        telegramBot.botConnect();
    }
}
