package com.bot.vagas;
import java.util.List;

public interface JobScraper {
    List<Job> search(String query);
}