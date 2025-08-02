package net.patelRestaurant.journal.App.cache;

import jakarta.annotation.PostConstruct;
import net.patelRestaurant.journal.App.Repository.ConfigJournalAppRepository;
import net.patelRestaurant.journal.App.entity.ConfigJournalAppEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
public class AppCache {
    public enum keys{
        weather_api;
    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String, String> appCache;

    @PostConstruct
    public void init(){
        appCache = new HashMap<>();
        List<ConfigJournalAppEntity> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntity configJournalAppEntity : all) {
            appCache.put(configJournalAppEntity.getKey(), configJournalAppEntity.getValue());
        }
    }
}
