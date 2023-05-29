package User;


public class UserInfo {
    public static class UserSettingsEntry {
        private final Long chatId;
        private final UserSettings userSettings;

        public UserSettingsEntry(Long chatId, UserSettings userSettings) {
            this.chatId = chatId;
            this.userSettings = userSettings;
        }

        public Long getChatId() {
            return chatId;
        }

        public UserSettings getUserSettings() {
            return userSettings;
        }
    }
}
