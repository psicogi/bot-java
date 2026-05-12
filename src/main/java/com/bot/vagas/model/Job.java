package com.bot.vagas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;

@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String company;

    @Column(unique = true)
    private String link;

    public Job() {}

    public Job(String title, String company, String link) {
        this.title = title;
        this.company = company;
        this.link = link;
    }

    public String getTitle() { return title; }
    public String getCompany() { return company; }
    public String getLink() { return link; }
}