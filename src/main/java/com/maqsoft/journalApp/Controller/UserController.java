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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService myuserService;

    @GetMapping
    public List<User> getAllUser(){
        return myuserService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        System.out.println(user);
        myuserService.saveentry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user , @PathVariable String userName){
        User userindb=myuserService.findbyUsername(userName);
        if(userindb!=null){
            userindb.setUserName(user.getUserName());
            userindb.setPassWord(user.getPassWord());
            myuserService.saveentry(userindb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
