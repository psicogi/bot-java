package com.bot.vagas.scheduler;

import com.bot.vagas.model.Job;
import com.bot.vagas.repository.JobRepository;
import com.bot.vagas.service.GupyScraper;
import com.bot.vagas.service.TelegramBot;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class JobScheduler {

    private final GupyScraper gupyScraper;
    private final TelegramBot telegramBot;
    private final JobRepository jobRepository;

    private final Set<String> linksEnviados = new HashSet<>();

    public JobScheduler(GupyScraper gupyScraper, TelegramBot telegramBot, JobRepository jobRepository) {
        this.gupyScraper = gupyScraper;
        this.telegramBot = telegramBot;
        this.jobRepository = jobRepository;
    }

    @Scheduled(fixedRate = 1800000)
    public void executarBusca() {
        System.out.println("Buscando novas vagas fresquinhas...");

        List<Job> vagasEncontradas = gupyScraper.search("java");

        for (Job vaga : vagasEncontradas) {

            if (jobRepository.existsByLink(vaga.getLink())) {
                continue;
            }

            enviarParaTelegram(vaga);
            jobRepository.save(vaga);
        }
    }

    private void enviarParaTelegram(Job vaga) {
        String texto = String.format(
                "Nova Vaga de Java!\n\n" +
                        "%s\n" +
                        "\nEmpresa: %s\n\n" +
                        "Candidate-se: %s",
                vaga.getTitle(), vaga.getCompany(), vaga.getLink()
        );

        long meuChatId = 2119776768L;
        telegramBot.responder(meuChatId, texto);
    }
}