package com.example.journal.appCache;

import com.example.journal.entity.ConfigJournalApp;
import com.example.journal.repository.ConfigAppRepos;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    @Autowired
    private ConfigAppRepos configAppRepos;

    public Map<String,String> APP_CACHE = new HashMap<>();

    @PostConstruct
    public void init() {
        List<ConfigJournalApp> all = configAppRepos.findAll();
        for (ConfigJournalApp configJournalApp : all) {
            APP_CACHE.put(configJournalApp.getKey(), configJournalApp.getValue());
        }
    }
}
