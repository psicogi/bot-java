package com.bot.vagas.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String mensagemRecebida = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            System.out.println("================================");
            System.out.println("NOVA MENSAGEM RECEBIDA!");
            System.out.println("USUÁRIO: " + update.getMessage().getFrom().getFirstName());
            System.out.println("ID DO CHAT: " + chatId);
            System.out.println("TEXTO: " + mensagemRecebida);
            System.out.println("================================");

            if (mensagemRecebida.equals("/start")) {
                responder(chatId, "Oii! Eu sou o Spidey Bot! Em breve te mandarei algumas vagas em Java :)");
            }
        }
    }

    public void responder(long chatId, String texto) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(texto);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}