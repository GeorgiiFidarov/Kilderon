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
    
//    public String chatGPT(String text) throws Exception {
//        String url = "https://api.openai.com/v1/chat/completions";
//        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
//
//        con.setRequestMethod("POST");
//        con.setRequestProperty("Content-Type", "application/json");
//        con.setRequestProperty("Authorization", "Bearer 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");
//
//        JSONObject data = new JSONObject();
//        Map<String,String> map = new HashMap<>();
//        map.put("role","assistant");
//        map.put("content",text);
//        List<Map<String,String>> listOfMap = Arrays.asList(map);
//        data.put("model", "gpt-3.5-turbo");
//        data.put("messages",listOfMap);
//        data.put("temperature", 1.0);
//        con.setDoOutput(true);
//        con.getOutputStream().write(data.toString().getBytes());
//
//        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
//                .reduce((a, b) -> a + b).get();
//        System.out.println(text);
//
//        return new JSONObject(output)
//                .getJSONArray("choices")
//                .getJSONObject(0)
//                .getJSONObject("message")
//                .getString("content");
//    }
    @Override
    public String getBotUsername() {
        return "KilderonBot";
    }

    @Override
    public String getBotToken() {
        return "6025656044:AAGg2q4CZInyMubKy9kUyOBOP4t1qQWg9UA";
    }
}
