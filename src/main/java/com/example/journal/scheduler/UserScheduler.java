package com.example.journal.scheduler;

import com.example.journal.entity.JournalEntity;
import com.example.journal.entity.User;
import com.example.journal.enums.Sentiment;
import com.example.journal.repository.UserRepositoryImpl;
import com.example.journal.service.EmailService;
import com.example.journal.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepositoryImpl userService;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Scheduled( cron = "0 0 9 * * SUN")
    public void fetchUserAndSendSAMail() {
        List<User> Users = userService.getUsersForSA();
        for (User user : Users) {
            List<JournalEntity> journals = user.getJournalEntityList();
            List<String> recentJournals = journals.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getSentiment().toString()).collect(Collectors.toList());
            String entry = String.join(" ", recentJournals);
            String sentiment = sentimentAnalysisService.getSentiment(entry);
            emailService.sendEmail(user.getEmail(), "Sentiment for Last 7 days", sentiment);
        }

    }
}
