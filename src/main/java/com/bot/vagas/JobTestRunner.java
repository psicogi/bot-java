package com.bot.vagas;

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
            System.out.println("Vaga: " + vaga.title() + " | Empresa: " + vaga.company());
            System.out.println("Link: " + vaga.link());
            System.out.println("------------------------------------");
        });
    }
}