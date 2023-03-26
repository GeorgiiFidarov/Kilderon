package ru.fidarov.KilderonBOT.Kilderon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.fidarov.KilderonBOT.Kilderon.config.KilderonBOT;

@SpringBootApplication
public class KilderonApplication {
	public static void main(String[] args) throws TelegramApiException {
		SpringApplication.run(KilderonApplication.class, args);
		TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
		try {
			botsApi.registerBot(new KilderonBOT());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
