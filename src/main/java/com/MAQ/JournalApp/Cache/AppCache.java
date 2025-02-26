package com.MAQ.JournalApp.Cache;

import com.MAQ.JournalApp.Entity.ConfigJournalApp;
import com.MAQ.JournalApp.repository.ConfigJournalAppRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys{
        Weather_API_KEY
    }

    @Autowired
    private ConfigJournalAppRepo configjournalapprepo;

    public Map<String,String> appcache = new HashMap<>();

    @PostConstruct
    public void init(){
        List<ConfigJournalApp> all = configjournalapprepo.findAll();
        for(ConfigJournalApp x:all){
            appcache.put(x.getKey(),x.getValue());
        }
    }




}
