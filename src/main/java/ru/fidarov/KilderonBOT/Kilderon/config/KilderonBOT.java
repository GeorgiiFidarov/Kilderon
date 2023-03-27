package ru.fidarov.KilderonBOT.Kilderon.config;

import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.fidarov.KilderonBOT.Kilderon.GPT.ChatGPT;
import ru.fidarov.KilderonBOT.Kilderon.GPT.ChatGPTImager;
import ru.fidarov.KilderonBOT.Kilderon.GPT.ChatGPTModel;

@Configuration
public class KilderonBOT extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            MessageEntity messageEntity = new MessageEntity();
            String messageText = update.getMessage().getText();
            if (update.getMessage().getText().contains("@KilderonBot")){
                ChatGPT chatGPT = new ChatGPT(String.valueOf(update.getMessage().getChat().getId()));
                long chatId = update.getMessage().getChatId();
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                try {
                    message.setText(chatGPT.chatGPT(messageText));
                    execute(message);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            if (update.getMessage().getText().contains("@call")){
                ChatGPTModel chatGPTModel = new ChatGPTModel(String.valueOf(update.getMessage().getChat().getId()));
                long chatId = update.getMessage().getChatId();
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                try {
                    message.setText(chatGPTModel.chatGPT(messageText));
                    execute(message);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            if (update.getMessage().getText().contains("@image")){
                long chatId = update.getMessage().getChatId();
                ChatGPTImager chatGPTImager = new ChatGPTImager();
                SendMessage message = new SendMessage();
                message.setChatId(chatId);
                try {
                    message.setText(chatGPTImager.chatGPT(messageText));
                    execute(message);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
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
