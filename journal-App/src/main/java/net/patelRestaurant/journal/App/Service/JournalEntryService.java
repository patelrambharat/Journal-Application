package net.patelRestaurant.journal.App.Service;

import net.patelRestaurant.journal.App.Repository.JurnalEntryRepo;
import net.patelRestaurant.journal.App.entity.JournalEntry;
import net.patelRestaurant.journal.App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {
            @Autowired
            private JurnalEntryRepo journalEntryRepo;

            @Autowired
            private UserEntryService userEntryService;

            public void saveEntry(JournalEntry journalEntry, String userName){
                User user = userEntryService.findByUserName(userName);
                journalEntry.setDate(LocalDateTime.now());
                JournalEntry saved = journalEntryRepo.save(journalEntry);
                user.getJournalEntryList().add(saved);
                userEntryService.saveEntry(user);
            }
            public List<JournalEntry> getAll(){
                return journalEntryRepo.findAll();
            }
            public Optional<JournalEntry> findById(ObjectId id){
                return journalEntryRepo.findById(id);
            }
            public void deleteById(ObjectId id, String userName){
                User user = userEntryService.findByUserName(userName);
                user.getJournalEntryList().removeIf(x -> x.getId().equals(id));
                userEntryService.saveEntry(user);
                 journalEntryRepo.deleteById(id);
            }

}
