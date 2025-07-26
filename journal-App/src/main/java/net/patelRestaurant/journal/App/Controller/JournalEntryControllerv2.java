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
            public ResponseEntity<?> getAll(){

                List<JournalEntry> all = journalEntryService.getAll();
                if(all != null && !all.isEmpty()){
                    return new ResponseEntity<>(all, HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
            // POST API - Add new journal entry

            @PostMapping
            public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
                try{
                    myEntry.setDate(LocalDateTime.now());
                    journalEntryService.saveEntry(myEntry);
                    return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
                }
                catch (Exception e){
                    return new ResponseEntity<>(myEntry, HttpStatus.BAD_REQUEST);
                }

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
            public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId){
                     journalEntryService.deleteById(myId);
                     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            @PutMapping("/id/{id}")
            public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
                JournalEntry old = journalEntryService.findById(id).orElse(null);
                if(old != null){
                    old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                    old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                    journalEntryService.saveEntry(old);
                    return new ResponseEntity<>(old, HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
}
