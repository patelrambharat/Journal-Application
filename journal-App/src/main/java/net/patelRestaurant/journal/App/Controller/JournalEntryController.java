package net.patelRestaurant.journal.App.Controller;

import net.patelRestaurant.journal.App.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

            private Map<Long , JournalEntry> journalEntryMap = new HashMap<>();
            // GET API - Fetch all journal entries
            @GetMapping("/all")
            public List<JournalEntry> getAll(){
                return new ArrayList<>(journalEntryMap.values());
            }
            // POST API - Add new journal entry
            @PostMapping("/add")
            public void createEntry(@RequestBody JournalEntry myEntry){
                journalEntryMap.put(myEntry.getId(), myEntry);
            }
            @GetMapping("/id/{myId}")
            public JournalEntry getJournalEntryById(@PathVariable Long myId){
                        return journalEntryMap.get(myId);
            }
            @DeleteMapping("/id/{myId}")
            public JournalEntry deleteJournalEntryById(@PathVariable Long myId){
                        return journalEntryMap.remove(myId);
            }
            @PutMapping("/id/{id}")
            public JournalEntry updateJournalEntryById(@PathVariable Long id, @RequestBody JournalEntry myEntry){
                return journalEntryMap.put(id, myEntry);
            }
}
