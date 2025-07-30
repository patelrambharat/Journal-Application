package net.patelRestaurant.journal.App.Service;

import net.patelRestaurant.journal.App.Repository.JurnalEntryRepo;
import net.patelRestaurant.journal.App.entity.JournalEntry;
import net.patelRestaurant.journal.App.entity.User;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {
            @Autowired
            private JurnalEntryRepo journalEntryRepo;

            @Autowired
            private UserEntryService userEntryService;


            @Transactional
            public void saveEntry(JournalEntry journalEntry, String userName){
                try{
                    User user = userEntryService.findByUserName(userName);
                    journalEntry.setDate(LocalDateTime.now());
                    JournalEntry saved = journalEntryRepo.save(journalEntry);
                    user.getJournalEntryList().add(saved);
                    userEntryService.saveEntry(user);
                }
                catch (Exception e){

                    throw  new RuntimeException("An error occured while saving the entry", e);
                }

            }

            public void saveEntry(JournalEntry journalEntry){
                journalEntryRepo.save(journalEntry);
            }
            public List<JournalEntry> getAll(){
                return journalEntryRepo.findAll();
            }
            public Optional<JournalEntry> findById(ObjectId id){
                return journalEntryRepo.findById(id);
            }

            @Transactional
            public boolean deleteById(ObjectId id, String userName){
                boolean removed = false;
                try {
                    User user = userEntryService.findByUserName(userName);

                     removed = user.getJournalEntryList().removeIf(x -> x.getId().equals(id));
                    if (removed) {
                        userEntryService.saveEntry(user);
                        journalEntryRepo.deleteById(id);
                    }

                }
                catch (Exception e){
                    System.out.println(e);
                    throw new RuntimeException("An error occurred with saving the entry.", e);
                }
                return removed;
            }


}
