package com.bot.vagas.config;

import com.bot.vagas.service.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(TelegramBot telegramBot) throws TelegramApiException {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            System.out.println("Registrando o bot no Telegram...");
            botsApi.registerBot(telegramBot);
            System.out.println("Bot registrado com sucesso!");
        } catch (TelegramApiException e) {
            System.err.println("Erro ao registrar o bot: " + e.getMessage());
            throw e;
        }
        return botsApi;
    }
}