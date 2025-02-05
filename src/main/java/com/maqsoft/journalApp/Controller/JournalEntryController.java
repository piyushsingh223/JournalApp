//package com.maqsoft.journalApp.Controller;
//
//import com.maqsoft.journalApp.Entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//
//    private Map<Long,JournalEntry> journalEntries = new HashMap<>();
//
//    @GetMapping("/get")
//    public List<JournalEntry> getAll(){
//        return new ArrayList<>(journalEntries.values());
//    }
//
//    @PostMapping("/post")
//    public boolean createEntry(@RequestBody JournalEntry myEntry){
//        journalEntries.put(myEntry.getId(),myEntry);
//        return true;
//    }
//
//    @GetMapping("id/{myid}")
//    public JournalEntry getJournalEntryById(@PathVariable Long myid){
//        return journalEntries.get(myid);
//    }
//
//    @DeleteMapping("id/{myid}")
//    public String deleteJournalEntryById(@PathVariable Long myid){
//        journalEntries.remove(myid);
//        return "entry deleted";
//    }
//
//    @PutMapping("id/{myid}")
//    public String updatejournalEntryBYId(@PathVariable Long myid,@RequestBody JournalEntry myentry){
//        journalEntries.put(myid,myentry);
//        return "Entry updated";
//    }
//
//}
