package com.bot.vagas.service;

import com.bot.vagas.model.Job;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GupyScraper implements JobScraper {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Job> search(String query) {
        List<Job> jobs = new ArrayList<>();
        String url = "https://portal.api.gupy.io/api/v1/jobs?jobName=" + query + "&limit=20&sort=publishedAt,desc";

        try {
            String response = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .execute()
                    .body();

            JsonNode root = objectMapper.readTree(response);
            JsonNode data = root.path("data");

            if (data.isArray()) {
                for (JsonNode node : data) {
                    String status = node.path("status").asText();
                    boolean isClosed = node.path("isClosed").asBoolean();

                    if (isClosed || !"published".equals(status)) {
                        continue;
                    }

                    String title = node.path("name").asText();
                    if (!title.toLowerCase().contains("java") || title.toLowerCase().contains("javascript")) {

                    }

                    String company = node.path("companyName").asText();
                    String jobUrl = node.path("jobUrl").asText();

                    jobs.add(new Job(title, company, jobUrl));
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao filtrar vagas da Gupy: " + e.getMessage());
        }
        return jobs;
    }
}