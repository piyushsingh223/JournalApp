package com.MAQ.JournalApp.Service;

import com.MAQ.JournalApp.Entity.JournalEntry;
import com.MAQ.JournalApp.Entity.User;
import com.MAQ.JournalApp.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

//@Component
@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo JournalEntryRepo;

    @Autowired
    private UserService myuserservice;


//    public void SaveEntry(JournalEntry JournalEntry){
//        JournalEntryRepo.save(JournalEntry);
//    }

    @Transactional
    public void saveentry(JournalEntry myentry , String userName){
        try {
            User user = myuserservice.findbyUsername(userName);
            myentry.setDate(LocalDateTime.now());
            JournalEntry saved = JournalEntryRepo.save(myentry);
            user.getJournalEntries().add(saved);
            //user.setUserName(null);
            myuserservice.saveentry(user);
        } catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An Error accured while saving the entry",e);
        }
    }

    public void saveentrytemp(JournalEntry myentry){
        JournalEntryRepo.save(myentry);
    }

    public List<JournalEntry> getAll(){
        return JournalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findbyid(ObjectId id){
        return JournalEntryRepo.findById(id);
    }

    @Transactional
    public boolean deletebyId(ObjectId id,String userName){
        boolean removed = false;
        try {
            User user = myuserservice.findbyUsername(userName);
            removed =user.getJournalEntries().removeIf(x->x.getId().equals(id));
            if(removed){
                myuserservice.saveentry(user);
                JournalEntryRepo.deleteById(id);
            }
        } catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error has occured while deleting the entry ",e);
        }
        return removed;
    }
}
