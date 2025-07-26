package net.patelRestaurant.journal.App.Controller;

import net.patelRestaurant.journal.App.Service.JournalEntryService;
import net.patelRestaurant.journal.App.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

            @Autowired
            private JournalEntryService journalEntryService;

            // GET API - Fetch all journal entries
            @GetMapping("/all")
            public List<JournalEntry> getAll(){

                return journalEntryService.getAll();
            }
            // POST API - Add new journal entry

            @PostMapping
            public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
                myEntry.setDate(LocalDateTime.now());
                journalEntryService.saveEntry(myEntry);
               return myEntry;
            }
            @GetMapping("/id/{myId}")
            public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
                Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
                if(journalEntry.isPresent()){
                    return  new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            @DeleteMapping("/id/{myId}")
            public boolean deleteJournalEntryById(@PathVariable ObjectId myId){
                     journalEntryService.deleteById(myId);
                     return true;
            }
            @PutMapping("/id/{id}")
            public JournalEntry updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
                JournalEntry old = journalEntryService.findById(id).orElse(null);
                if(old != null){
                    old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                    old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());

                }
                journalEntryService.saveEntry(old);
                return old;
            }
}
