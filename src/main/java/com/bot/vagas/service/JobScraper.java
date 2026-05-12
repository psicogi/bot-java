package com.bot.vagas.service;
import com.bot.vagas.model.Job;

import java.util.List;

public interface JobScraper {
    List<Job> search(String query);
}