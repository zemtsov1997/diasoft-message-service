package ru.diasoft.message.service.model.telegram;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import ru.diasoft.message.service.entity.SocialUser;
import ru.diasoft.message.service.model.UniversalNetworkBusinessLogic;
import ru.diasoft.message.service.service.SocialUserService;

public class TelegramBot extends TelegramLongPollingBot {

    private static final Logger logger = LogManager.getLogger(TelegramBot.class);

    private String botName;

    private String authToken;

    private UniversalNetworkBusinessLogic universalNetworkBusinessLogic;

    private SocialUserService socialUserService;

    public TelegramBot(String botName, String authToken, UniversalNetworkBusinessLogic universalNetworkBusinessLogic, SocialUserService socialUserService) {
        this.botName = botName;
        this.authToken = authToken;
        this.universalNetworkBusinessLogic = universalNetworkBusinessLogic;
        this.socialUserService = socialUserService;
    }

    /**
     * Метод для приема сообщений.
     * @param update Содержит сообщение от пользователя.
     */
    @Override
    public void onUpdateReceived(Update update) {
        SocialUser socialUser = socialUserService.findByTelegramChatId(update.getMessage().getChatId().toString());
        String message = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), universalNetworkBusinessLogic.setMessage(message, socialUser != null ? socialUser.getId() : null));
    }

    /**
     * Метод для настройки сообщения и его отправки.
     * @param chatId id чата
     * @param s Строка, которую необходимот отправить в качестве сообщения.
     */
    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            logger.error(e);
        }
    }

    public synchronized void sendImage(String chatId, String photoStr, String caption) {
        SendPhoto photo = new SendPhoto()
                .setChatId(chatId)
                .setPhoto(photoStr)
                .setCaption(caption);
        try {
            this.sendPhoto(photo);
        } catch (TelegramApiException e) {
            logger.error(e);
        }
    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        return botName;
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     * @return token для бота
     */
    @Override
    public String getBotToken() {
        return authToken;
    }

    public String getBotName() {
        return botName;
    }
    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getAuthToken() {
        return authToken;
    }
    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

}
