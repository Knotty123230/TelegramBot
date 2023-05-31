package bot;

import currency.Currency;
import currency.MonoCurrency;
import currency.inpl.MonoCurrencyImpl;
import currency.inpl.NBUCurrencyImpl;
import currency.inpl.PrivatCurrencyImpl;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.BotService;

import java.io.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageWithSave {
    private final Map<String,List<Double>> savedRates;
    private final String savedRatesFilePath = "saved_rates.ser";
    private double currenceRate1;
    private double currenceRate2;


    public MessageWithSave() {
        savedRates = loadSavedRates();
    }

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
                    try {


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
                                        "USD купівля: " + format + "\n" +
                                        "EUR Купівля: " + format1 + "\n" +
                                        "USD Продаж: " + format2 + "\n" +
                                        "EUR Продаж: " + format3 + "\n");
                    }catch (Exception e){
                        List<Double> rateUsd = savedRates.get("USD" + " ✅");
                        List<Double> rateEur = savedRates.get("EUR" + " ✅");
                        String formattedUSD = decimalFormat != null ? decimalFormat.format(rateUsd.get(0)) : null;
                        String formattedUSDSell = decimalFormat != null ? decimalFormat.format(rateUsd.get(1)) : null;
                        String formattedEUREur = decimalFormat != null ?decimalFormat.format(rateEur.get(0)) : null;
                        String formattedEURSell = decimalFormat != null ? decimalFormat.format(rateEur.get(1)) : null;
                        return BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),
                                "Курс Монобанк USD EUR\n" +
                                        "USD купівля: " + formattedUSDSell + "\n" +
                                        "EUR Купівля: " + formattedEURSell + "\n" +
                                        "USD Продаж: " +  formattedUSD + "\n" +
                                        "EUR Продаж: " + formattedEUREur + "\n");

                    }
                }
                monoCurrency = new MonoCurrencyImpl();
                try {
                    currenceRate1 = monoCurrency.getCurrenceRateSell(switch (buttonCurrency) {
                        case "USD ✅" -> MonoCurrency.USD;
                        case "EUR ✅" -> MonoCurrency.EUR;
                        default -> throw new RuntimeException();
                    });
                    formattedRateSell = decimalFormat != null ? decimalFormat.format(currenceRate1) : null;
                    currenceRate2 = monoCurrency.getCurrenceRate(switch (buttonCurrency) {
                        case "USD ✅" -> MonoCurrency.USD;
                        case "EUR ✅" -> MonoCurrency.EUR;
                        default -> throw new RuntimeException();
                    });
                    formattedRate = decimalFormat != null ? decimalFormat.format(currenceRate2) : null;
                    System.out.println("я в моно");
                    sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),
                            "Курс Монобанк: " + switch (buttonCurrency) {
                                case "USD ✅" -> "USD";
                                case "EUR ✅" -> "EUR";
                                default -> throw new RuntimeException();
                            } +
                                    "\n" +
                                    "Купівля: " + formattedRate + "\n" +
                                    "Продаж: " + formattedRateSell);
                    savedRates.put(buttonCurrency, List.of(currenceRate1, currenceRate2));
                    saveSavedRates();
                } catch (Exception e) {
                    System.out.println("Помилка отримання курсу з Монобанку. Запрошую використати останнє збережене значення.");
                    List<Double> savedRateValues = savedRates.get(buttonCurrency);
                    if (savedRateValues != null && savedRateValues.size() == 2) {
                        formattedRate = decimalFormat != null ? decimalFormat.format(savedRateValues.get(0)) : null;
                        formattedRateSell = decimalFormat != null ? decimalFormat.format(savedRateValues.get(1)) : null;
                        sendMessage = BotService.sendMessage(update.getCallbackQuery().getMessage().getChatId(),
                                "Помилка отримання курсу з Монобанку. Застосовується останнє збережене значення:\n" +
                                        "Курс Монобанк: " + switch (buttonCurrency) {
                                    case "USD ✅" -> "USD";
                                    case "EUR ✅" -> "EUR";
                                    default -> throw new RuntimeException();
                                } +
                                        "\n" +
                                        "Покупка : " + formattedRateSell + "\n" +
                                        "продажа : " + formattedRate);
                    }

                }
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
                    return BotService.sendMessage(chatid,
                            "Курс ПРИВАТБАНК: USD, EUR\n" +
                                    "USD Купівля: " + format + "\n" +
                                    "EUR Купівля: " + format1 + "\n" +
                                    "USD Продаж: " + format2 + "\n" +
                                    "EUR Продаж: " + format3 + "\n");
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
                        "Курс ПРИВАТБАНК: " + switch (buttonCurrency) {
                            case "USD" + " ✅" -> "USD";
                            case "EUR" + " ✅" -> "EUR";
                            default -> throw new RuntimeException();
                        } + "\n" +
                                "Купівля: " + formattedRate + "\n" +
                                "Продаж: " + formattedRateSell);
            }


            case "НБУ ✅" -> {
                if (buttonCurrency.equals(("USD" + " ✅" + "EUR" + " ✅"))) {
                    double usdBuy = new NBUCurrencyImpl().getCurrenceRate(Currency.USD);
                    double eurBuy = new NBUCurrencyImpl().getCurrenceRate(Currency.EUR);
                    String format = decimalFormat != null ? decimalFormat.format(usdBuy) : null;
                    String format1 = decimalFormat != null ? decimalFormat.format(eurBuy) : null;

                    return BotService.sendMessage(chatid,
                            "Курс НБУ: USD, EUR\n" +
                                    "USD Купівля: " + format + "\n" +
                                    "EUR Купівля: " + format1 + "\n");
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
                        "Курс НБУ: "
                                + switch (buttonCurrency) {
                            case "USD" + " ✅" -> "USD";
                            case "EUR" + " ✅" -> "EUR";
                            default -> throw new RuntimeException();
                        }

                                + ":\n" +
                                "Купівля: " + formattedRate + "\n");
            }
        }


        return sendMessage;
    }


    private Map<String, List<Double>> loadSavedRates() {
        try (FileInputStream fileIn = new FileInputStream(savedRatesFilePath);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (Map<String, List<Double>>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Не вдалося завантажити збережені ставки. Створюється новий порожній мап.");
            return new HashMap<>();
        }
    }

    private void saveSavedRates() {
        try (FileOutputStream fileOut = new FileOutputStream(savedRatesFilePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(savedRates);
        } catch (IOException e) {
            System.out.println("Failed to save rates to file.");
        }
    }

}
