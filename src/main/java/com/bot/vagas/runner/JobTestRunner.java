package com.bot.vagas.runner;

import com.bot.vagas.model.Job;
import com.bot.vagas.service.GupyScraper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class JobTestRunner implements CommandLineRunner {

    private final GupyScraper gupyScraper;

    public JobTestRunner(GupyScraper gupyScraper) {
        this.gupyScraper = gupyScraper;
    }

    @Override
    public void run(String... args) {
        System.out.println("--- TESTANDO BUSCA NA GUPY (JAVA) ---");
        List<Job> vagasGupy = gupyScraper.search("java");

        vagasGupy.forEach(vaga -> {
            System.out.println("Vaga: " + vaga.getTitle() + " | Empresa: " + vaga.getCompany());
            System.out.println("Link: " + vaga.getLink());
            System.out.println("------------------------------------");
        });
    }
}