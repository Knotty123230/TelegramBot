package User;

import constants.PageLabels;

import java.util.ArrayList;
import java.util.List;

import static constants.PageLabels.currEurLabel;
import static constants.PageLabels.currUsdLabel;

public class UserSettings {

    private final List<String> buttonsCurrency = new ArrayList<>(List.of(
            "USD ✅",
            "EUR",
            "OK"
    ));
    private final List<String> buttonsBank = new ArrayList<>(List.of(
            "НБУ",
            "ПриватБанк ✅",
            "Монобанк",
            "OK"));
    private final List<String> buttonsSins = new ArrayList<>(List.of(
            "1 ✅",
            "2",
            "3",
            "4",
            "OK"));
    private final List<String> queryCurrency = new ArrayList<>(List.of(
            currUsdLabel + " ✅",
            currEurLabel,
            "OK"
    ));
    private final List<String> queryBank = new ArrayList<>(List.of(
            PageLabels.bankNBULabel,
            PageLabels.bankPrivatLabel,
            PageLabels.bankMonoLabel,
            "OK"));
    private final List<String> querySins = new ArrayList<>(List.of(
            "1",
            "2",
            "3",
            "4",
            "OK"));


    public String notificationTime = "";

    public void setSchedulerRunning(boolean schedulerRunning) {
        isSchedulerRunning = schedulerRunning;
    }

    private boolean isSchedulerRunning = false;

    public boolean isSchedulerRunning() {
        return isSchedulerRunning;
    }
    private boolean isNotificationsEnabled = true;

    public boolean isNotificationsEnabled() {
        return isNotificationsEnabled;
    }

    public void setNotificationsEnabled(boolean notificationsEnabled) {
        isNotificationsEnabled = notificationsEnabled;
    }

    @Override
    public String toString() {
        String notification = "";
        return "UserSettings{" +
                "buttonsCurrency=" + buttonsCurrency +
                ", buttonsBank=" + buttonsBank +
                ", buttonsSins=" + buttonsSins +
                ", queryCurrency=" + queryCurrency +
                ", queryBank=" + queryBank +
                ", querySins=" + querySins +
                ", notificationTime='" + notificationTime + '\'' +
                ", notification='" + notification + '\'' +
                '}';
    }

    public List<String> getButtonsCurrency() {
        return buttonsCurrency;
    }

    public List<String> getButtonsBank() {
        return buttonsBank;
    }

    public List<String> getButtonsSins() {
        return buttonsSins;
    }

    public List<String> getQueryCurrency() {
        return queryCurrency;
    }

    public List<String> getQueryBank() {
        return queryBank;
    }

    public List<String> getQuerySins() {
        return querySins;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }
}