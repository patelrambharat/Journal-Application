package net.patelRestaurant.journal.App.Controller;

import net.patelRestaurant.journal.App.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {


            // GET API - Fetch all journal entries
            @GetMapping("/all")
            public List<JournalEntry> getAll(){
                return null;
            }
            // POST API - Add new journal entry
            @PostMapping("/add")
            public boolean createEntry(@RequestBody JournalEntry myEntry){
               return true;
            }
            @GetMapping("/id/{myId}")
            public JournalEntry getJournalEntryById(@PathVariable Long myId){
                    return null;
            }
            @DeleteMapping("/id/{myId}")
            public JournalEntry deleteJournalEntryById(@PathVariable Long myId){
                    return null;
            }
            @PutMapping("/id/{id}")
            public JournalEntry updateJournalEntryById(@PathVariable Long id, @RequestBody JournalEntry myEntry){
                return null;
            }
}
