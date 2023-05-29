package bot;

import currency.Currency;
import currency.MonoCurrency;
import currency.inpl.MonoCurrencyImpl;
import currency.inpl.NBUCurrencyImpl;
import currency.inpl.PrivatCurrencyImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.BotService;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class MessageWithSave {

    public SendMessage getUpdate(Long chatid, List<String> buttonsSigns, List<String> buttonsBank, List<String> buttonsCurrency, Update update) throws RuntimeException {
        String buttonSigns = buttonsSigns.stream()
                .filter(it -> it.contains(" ✅"))
                .collect(Collectors.joining());
        String buttonBank = buttonsBank.stream()
                .filter(it -> it.contains(" ✅"))
                .collect(Collectors.joining());
        String buttonCurrency = buttonsCurrency.stream()
                .filter(it -> it.contains(" ✅"))
                .collect(Collectors.joining());
        System.out.println(buttonCurrency);
        SendMessage sendMessage = null;
        NBUCurrencyImpl nbuCurrency;
        MonoCurrencyImpl monoCurrency;
        PrivatCurrencyImpl privatCurrency;
        DecimalFormat decimalFormat = null;
        String formattedRate;
        String formattedRateSell;
        double currenceRate;
        System.out.println("я в методі");
        switch (buttonSigns) {
            case "1 ✅" -> decimalFormat = new DecimalFormat("#.0");
            case "2 ✅" -> decimalFormat = new DecimalFormat("#.00");
            case "3 ✅" -> decimalFormat = new DecimalFormat("#.000");
            case "4 ✅" -> decimalFormat = new DecimalFormat("#.0000");
        }

        switch (buttonBank) {
            case "Монобанк ✅" -> {
                if (buttonCurrency.equals(("USD" + " ✅" + "EUR" + " ✅"))) {
                    double usdBuy = new MonoCurrencyImpl().getCurrenceRate(MonoCurrency.USD);
                    double eurBuy = new MonoCurrencyImpl().getCurrenceRate(MonoCurrency.EUR);
                    double currenceRateSell = new MonoCurrencyImpl().getCurrenceRateSell(MonoCurrency.USD);
                    double currenceRateSell1 = new MonoCurrencyImpl().getCurrenceRateSell(MonoCurrency.EUR);
                    String format = decimalFormat != null ? decimalFormat.format(usdBuy) : null;
                    String format1 = decimalFormat != null ? decimalFormat.format(eurBuy) : null;
                    String format2 = decimalFormat != null ? decimalFormat.format(currenceRateSell) : null;
                    String format3 = decimalFormat != null ? decimalFormat.format(currenceRateSell1) : null;
                    return BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),
                            "Курс Монобанк USD EUR\n" +
                                    "USDBUY " + format + "\n" +
                                    "EURBUY " + format1 + "\n" +
                                    "USDSELL " + format2 + "\n" +
                                    "EURSELL " + format3 + "\n");
                }
                monoCurrency = new MonoCurrencyImpl();
                currenceRate = monoCurrency.getCurrenceRateSell(switch (buttonCurrency) {
                    case "USD" + " ✅":
                        yield MonoCurrency.USD;
                    case "EUR" + " ✅":
                        yield MonoCurrency.EUR;
                    default:
                        throw new RuntimeException();
                });
                formattedRateSell = decimalFormat != null ? decimalFormat.format(currenceRate) : null;
                System.out.println("я в моно");

                currenceRate = monoCurrency.getCurrenceRate(switch (buttonCurrency) {
                    case "USD" + " ✅":
                        yield MonoCurrency.USD;
                    case "EUR" + " ✅":
                        yield MonoCurrency.EUR;
                    default:
                        throw new RuntimeException();
                });

                formattedRate = decimalFormat != null ? decimalFormat.format(currenceRate) : null;
                System.out.println("я в моно");
                sendMessage = BotService.sendMessage(chatid,
                        "Курс МОНОБАНК " + switch (buttonCurrency) {
                            case "USD" + " ✅" -> "USD";
                            case "EUR" + " ✅" -> "EUR";
                            default -> throw new RuntimeException();
                        } +
                                "\n" +
                                "Покупка: " + formattedRate + "\n" +
                                "Продажа: " + formattedRateSell);

            }
            case "ПриватБанк ✅" -> {
                if (buttonCurrency.equals(("USD" + " ✅" + "EUR" + " ✅"))) {
                    double usdBuy = new PrivatCurrencyImpl().getCurrenceRate(Currency.USD);
                    double eurBuy = new PrivatCurrencyImpl().getCurrenceRate(Currency.EUR);
                    double currenceRateSell = new PrivatCurrencyImpl().getCurrenceRateSell(Currency.USD);
                    double currenceRateSell1 = new PrivatCurrencyImpl().getCurrenceRateSell(Currency.EUR);
                    String format = decimalFormat != null ? decimalFormat.format(usdBuy) : null;
                    String format1 = decimalFormat != null ? decimalFormat.format(eurBuy) : null;
                    String format2 = decimalFormat != null ? decimalFormat.format(currenceRateSell) : null;
                    String format3 = decimalFormat != null ? decimalFormat.format(currenceRateSell1) : null;
                    return BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),
                            "Курс ПРИВАТБАНК USD EUR\n" +
                                    "USDBUY " + format + "\n" +
                                    "EURBUY " + format1 + "\n" +
                                    "USDSELL " + format2 + "\n" +
                                    "EURSELL " + format3 + "\n");
                }
                privatCurrency = new PrivatCurrencyImpl();
                currenceRate = privatCurrency.getCurrenceRateSell(switch (buttonCurrency) {
                    case "USD ✅":
                        yield Currency.USD;
                    case "EUR ✅":
                        yield Currency.EUR;
                    default:
                        throw new RuntimeException();
                });
                formattedRateSell = decimalFormat != null ? decimalFormat.format(currenceRate) : null;

                currenceRate = privatCurrency.getCurrenceRate(switch (buttonCurrency) {
                    case "USD ✅":
                        yield Currency.USD;
                    case "EUR ✅":
                        yield Currency.EUR;
                    default:
                        throw new RuntimeException();
                });
                formattedRate = decimalFormat != null ? decimalFormat.format(currenceRate) : null;
                sendMessage = BotService.sendMessage(chatid,
                        "Курс ПРИВАТБАНК " + switch (buttonCurrency) {
                            case "USD" + " ✅" -> "USD";
                            case "EUR" + " ✅" -> "EUR";
                            default -> throw new RuntimeException();
                        } + "\n" +
                                "Покупка: " + formattedRate + "\n" +
                                "Продажа: " + formattedRateSell);
            }


            case "НБУ ✅" -> {
                if (buttonCurrency.equals(("USD" + " ✅" + "EUR" + " ✅"))) {
                    double usdBuy = new NBUCurrencyImpl().getCurrenceRate(Currency.USD);
                    double eurBuy = new NBUCurrencyImpl().getCurrenceRate(Currency.EUR);
                    String format = decimalFormat != null ? decimalFormat.format(usdBuy) : null;
                    String format1 = decimalFormat != null ? decimalFormat.format(eurBuy) : null;

                    return BotService.sendMessage(chatid,
                            "Курс НБУ USD EUR\n" +
                                    "USDBUY " + format + "\n" +
                                    "EURBUY " + format1 + "\n");
                }
                nbuCurrency = new NBUCurrencyImpl();
                currenceRate = nbuCurrency.getCurrenceRate(switch (buttonCurrency) {
                    case "USD" + " ✅":
                        yield Currency.USD;
                    case "EUR" + " ✅":
                        yield Currency.EUR;
                    default:
                        throw new RuntimeException();
                });
                formattedRate = decimalFormat != null ? decimalFormat.format(currenceRate) : null;
                sendMessage = BotService.sendMessage(chatid,
                        "Курс НБУ "
                                + switch (buttonCurrency) {
                            case "USD" + " ✅" -> "USD";
                            case "EUR" + " ✅" -> "EUR";
                            default -> throw new RuntimeException();
                        }

                                + ":\n" +
                                "Покупка: " + formattedRate + "\n");
            }
        }


        return sendMessage;
    }
}
