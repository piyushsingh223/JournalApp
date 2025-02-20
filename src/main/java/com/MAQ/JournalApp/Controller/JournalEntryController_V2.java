package com.MAQ.JournalApp.Controller;

import com.MAQ.JournalApp.Entity.JournalEntry;
import com.MAQ.JournalApp.Entity.User;
import com.MAQ.JournalApp.Service.JournalEntryService;
import com.MAQ.JournalApp.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/_journal")
public class JournalEntryController_V2 {

    @Autowired
    private JournalEntryService myjournalEntryService;

    @Autowired
    private UserService myuserservice;

    @GetMapping("/get")
    public ResponseEntity<?> getAllJournalEntriesofUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user=myuserservice.findbyUsername(userName);
        List<JournalEntry> all=user.getJournalEntries();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/post")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
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
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = myuserservice.findbyUsername(userName);
        List<JournalEntry> mylist=user.getJournalEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());
        if(!mylist.isEmpty()){
            Optional<JournalEntry> journalEntry = myjournalEntryService.findbyid(myid);
            if(journalEntry.isPresent()){
                return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


//    @DeleteMapping("id/{myid}")
//    public boolean deleteJournalEntryById(@PathVariable ObjectId myid){
//        myjournalEntryService.deletebyId(myid);
//        return true;
//    }


    @DeleteMapping("id/{myid}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myid){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        boolean removed = myjournalEntryService.deletebyId(myid,userName);
        if(removed){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("id/{myid}")
    public ResponseEntity<?> updatejournalEntryBYId(@PathVariable ObjectId myid,@RequestBody JournalEntry newentry){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User user = myuserservice.findbyUsername(userName);
        List<JournalEntry> collect = user.getJournalEntries().stream().filter(x->x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<JournalEntry> journalEntry=myjournalEntryService.findbyid(myid);
            if(journalEntry.isPresent()) {
                JournalEntry old = journalEntry.get();
                old.setTitle(newentry.getTitle() != null && !newentry.getTitle().equals("") ? newentry.getTitle() : old.getTitle());
                old.setContent(newentry.getContent() != null && !newentry.getContent().equals("") ? newentry.getContent() : old.getContent());
                myjournalEntryService.saveentrytemp(old);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
