package com.bot.vagas.service;

import com.bot.vagas.model.Job;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {

    public List<Job> fetchJavaJobs() {
        List<Job> jobs = new ArrayList<>();
        String url = "https://trampos.co/oportunidades?trv=java";

        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0")
                    .get();

            Elements vacancies = doc.select(".opportunity");

            for (Element v : vacancies) {
                String title = v.select(".name").text();
                String company = v.select(".company").text();
                String link = "https://trampos.co" + v.select("a").attr("href");

                if (title.toLowerCase().contains("java")) {
                    jobs.add(new Job(title, company, link));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao acessar o site: " + e.getMessage());
        }
        return jobs;
    }
}