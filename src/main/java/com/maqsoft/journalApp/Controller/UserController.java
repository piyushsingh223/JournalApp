package com.maqsoft.journalApp.Controller;

import com.maqsoft.journalApp.Entity.JournalEntry;
import com.maqsoft.journalApp.Entity.User;
import com.maqsoft.journalApp.Service.JournalEntryService;
import com.maqsoft.journalApp.Service.UserService;
import com.maqsoft.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private UserRepo userrepo;


//    @GetMapping
//    public List<User> getAllUser(){
//        return myuserService.getAll();
//    }


    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        User userindb=myuserService.findbyUsername(userName);
        userindb.setUserName(user.getUserName());
        userindb.setPassWord(user.getPassWord());
        myuserService.savenewuser(userindb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public  ResponseEntity<?> deleteUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
        userrepo.deleteByuserName(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
