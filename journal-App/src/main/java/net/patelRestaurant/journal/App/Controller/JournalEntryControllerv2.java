package net.patelRestaurant.journal.App.Controller;

import net.patelRestaurant.journal.App.Service.JournalEntryService;
import net.patelRestaurant.journal.App.Service.UserEntryService;
import net.patelRestaurant.journal.App.entity.JournalEntry;
import net.patelRestaurant.journal.App.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

            @Autowired
            private JournalEntryService journalEntryService;

            // GET API - Fetch all journal entries

            @Autowired
            private UserEntryService userEntryService;
            @GetMapping
            public ResponseEntity<?> getAllJournalEntriesOfUser(){
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String userName = authentication.getName();
                User user = userEntryService.findByUserName(userName);
                List<JournalEntry> all = user.getJournalEntryList();
                if(all != null && !all.isEmpty()){
                    return new ResponseEntity<>(all, HttpStatus.OK);
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
            // POST API - Add new journal entry

            @PostMapping
            public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
                try{
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    String userName = authentication.getName();
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
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String userName = authentication.getName();
                User user  = userEntryService.findByUserName(userName);
                List<JournalEntry> collect = user.getJournalEntryList().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
                if(!collect.isEmpty()){
                    Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
                    if(journalEntry.isPresent()){
                        return  new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
                    }
                }
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            @DeleteMapping("/id/{myId}")
            public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId ){
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String userName = authentication.getName();
                    boolean removed =  journalEntryService.deleteById(myId, userName);
                    if(removed){
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                    }
                    else{
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                    }

            }
            @PutMapping("id/{myId}")
            public ResponseEntity<?> updateJournalEntryById(@PathVariable ObjectId myId,
                                                            @RequestBody JournalEntry newEntry){
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                String userName = authentication.getName();
                User user  = userEntryService.findByUserName(userName);
                List<JournalEntry> collect = user.getJournalEntryList().stream().filter(x -> x.getId().equals(myId)).collect(Collectors.toList());
                if(!collect.isEmpty()){
                    Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
                    if(journalEntry.isPresent()){
                        JournalEntry old = journalEntry.get();
                        old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
                        old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
                        journalEntryService.saveEntry(old);
                        return new ResponseEntity<>(old, HttpStatus.OK);
                    }
                }
                JournalEntry old = journalEntryService.findById(myId).orElse(null);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
}
