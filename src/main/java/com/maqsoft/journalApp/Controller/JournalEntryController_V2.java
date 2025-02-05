package com.maqsoft.journalApp.Controller;

import com.maqsoft.journalApp.Entity.JournalEntry;
import com.maqsoft.journalApp.Entity.User;
import com.maqsoft.journalApp.Service.JournalEntryService;
import com.maqsoft.journalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController_V2 {

    @Autowired
    private JournalEntryService myjournalEntryService;

    @Autowired
    private UserService myuserservice;

    @GetMapping("/get/{userName}")
    public ResponseEntity<?> getAllJournalEntriesofUser(@PathVariable String userName){
        User user=myuserservice.findbyUsername(userName);
        List<JournalEntry> all=user.getJournalEntries();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/post/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
        try {
            myjournalEntryService.saveentry(myEntry,userName);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("id/{myid}")
//    public JournalEntry getJournalEntryById(@PathVariable ObjectId myid){
//        return myjournalEntryService.findbyid(myid).orElse(null);
//    }

    @GetMapping("id/{myid}")
    public JournalEntry getJournalEntryById(@PathVariable ObjectId myid){
        return myjournalEntryService.findbyid(myid).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


//    @DeleteMapping("id/{myid}")
//    public boolean deleteJournalEntryById(@PathVariable ObjectId myid){
//        myjournalEntryService.deletebyId(myid);
//        return true;
//    }

    @DeleteMapping("id/{userName}/{myid}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myid , @PathVariable String userName){
        Optional<JournalEntry> journalEntry = myjournalEntryService.findbyid(myid);
        if(journalEntry.isPresent()){
            myjournalEntryService.deletebyId(myid,userName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("id/{userName}/{myid}")
    public ResponseEntity<?> updatejournalEntryBYId(@PathVariable ObjectId myid,@PathVariable String userName,@RequestBody JournalEntry newentry){
        JournalEntry old = myjournalEntryService.findbyid(myid).orElse(null);
        if(old!=null) {
            old.setTitle(newentry.getTitle()!=null && !newentry.getTitle().equals("")? newentry.getTitle() : old.getTitle());
            old.setContent(newentry.getContent()!=null && !newentry.getContent().equals("")? newentry.getContent() : old.getContent());
            myjournalEntryService.saveentrytemp(old);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
