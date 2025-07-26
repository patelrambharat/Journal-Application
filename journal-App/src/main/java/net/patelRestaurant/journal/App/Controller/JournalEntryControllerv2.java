package net.patelRestaurant.journal.App.Controller;

import net.patelRestaurant.journal.App.Service.JournalEntryService;
import net.patelRestaurant.journal.App.Service.UserEntryService;
import net.patelRestaurant.journal.App.entity.JournalEntry;
import net.patelRestaurant.journal.App.entity.User;
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

            @Autowired
            private UserEntryService userEntryService;
            @GetMapping("{userName}")
            public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
                User user = userEntryService.findByUserName(userName);
                List<JournalEntry> all = user.getJournalEntryList();
                if(all != null && !all.isEmpty()){
                    return new ResponseEntity<>(all, HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
            // POST API - Add new journal entry

            @PostMapping("{userName}")
            public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry , @PathVariable String userName){
                try{

                    myEntry.setDate(LocalDateTime.now());
                    journalEntryService.saveEntry(myEntry, userName);
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
            @DeleteMapping("/id/{userName}/{myId}")
            public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId , @PathVariable String userName){
                     journalEntryService.deleteById(myId, userName);
                     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            @PutMapping("id/{userName}/{myId}")
            public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId id,
                                                            @RequestBody JournalEntry newEntry ,
                                                            @PathVariable String userName){
                JournalEntry old = journalEntryService.findById(id).orElse(null);
                if(old != null){
                    old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                    old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                    journalEntryService.saveEntry(old, user);
                    return new ResponseEntity<>(old, HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
}
