package ru.fidarov.KilderonBOT.Kilderon.config;

import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Configuration
public class KilderonBOT extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText(messageText);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return "KilderonBot";
    }

    @Override
    public String getBotToken() {
        return "6025656044:AAGg2q4CZInyMubKy9kUyOBOP4t1qQWg9UA";
    }
}