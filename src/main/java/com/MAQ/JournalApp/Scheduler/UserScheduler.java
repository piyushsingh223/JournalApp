package com.MAQ.JournalApp.Scheduler;

import com.MAQ.JournalApp.Cache.AppCache;
import com.MAQ.JournalApp.Entity.JournalEntry;
import com.MAQ.JournalApp.Entity.User;
import com.MAQ.JournalApp.Service.EmailService;
import com.MAQ.JournalApp.Service.SentimentAnalysisService;
import com.MAQ.JournalApp.repository.UserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class UserScheduler {

    @Autowired
    private EmailService emailservice;

    @Autowired
    private UserRepoImpl userrepo;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private AppCache appcache;

    @Scheduled(cron = "0 0 9 ? * SUN *")
    public void fetchUserAndSendMail(){
        List<User> users=userrepo.getUserForSA();
        for(User user:users){
            List<JournalEntry> all=user.getJournalEntries();
            List<String> filteredEntries = all.stream().filter(x -> x.getDate().isAfter
                    (LocalDateTime.now().minus(7, ChronoUnit.DAYS))).
                    map(x -> x.getContent()).collect(Collectors.toList());
            String Entry= String.join("",filteredEntries);
            String Sentiment = sentimentAnalysisService.getSentiment(Entry);
            emailservice.sendEmail(user.getEmail(),"sentiment analysis of last 7 days",Sentiment);
        }
    }

    @Scheduled(cron="0 0/10 * 1/1 * ? *")
    public void clearcache(){
        appcache.init();
    }
}
