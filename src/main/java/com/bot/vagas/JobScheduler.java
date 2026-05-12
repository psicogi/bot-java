package com.bot.vagas;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JobScheduler {

    private final GupyScraper gupyScraper;
    private final TelegramBot telegramBot;

    private final Set<String> linksEnviados = new HashSet<>();

    public JobScheduler(GupyScraper gupyScraper, TelegramBot telegramBot) {
        this.gupyScraper = gupyScraper;
        this.telegramBot = telegramBot;
    }

    @Scheduled(fixedRate = 1800000)
    public void executarBusca() {
        System.out.println("Buscando novas vagas fresquinhas...");

        List<Job> vagasEncontradas = gupyScraper.search("java");

        for (Job vaga : vagasEncontradas) {

            if (linksEnviados.contains(vaga.link())) {
                continue;
            }

            enviarParaTelegram(vaga);

            linksEnviados.add(vaga.link());
        }
    }

    private void enviarParaTelegram(Job vaga) {
        String texto = String.format(
                "Nova Vaga de Java!\n\n" +
                        "%s\n" +
                        "\nEmpresa: %s\n\n" +
                        "Candidate-se: %s",
                vaga.title(), vaga.company(), vaga.link()
        );

        long meuChatId = 2119776768L;
        telegramBot.responder(meuChatId, texto);
    }
}