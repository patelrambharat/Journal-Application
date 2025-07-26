package net.patelRestaurant.journal.App.Service;

import net.patelRestaurant.journal.App.Repository.JurnalEntryRepo;
import net.patelRestaurant.journal.App.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {
            @Autowired
            private JurnalEntryRepo journalEntryRepo;

            public void saveEntry(JournalEntry journalEntry){
                journalEntryRepo.save(journalEntry);
            }
            public List<JournalEntry> getAll(){
                return journalEntryRepo.findAll();
            }
            public Optional<JournalEntry> findById(ObjectId id){
                return journalEntryRepo.findById(id);
            }
            public void deleteById(ObjectId id){
                 journalEntryRepo.deleteById(id);
            }

}
