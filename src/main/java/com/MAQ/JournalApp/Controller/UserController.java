package com.MAQ.JournalApp.Controller;

import com.MAQ.JournalApp.Entity.JournalEntry;
import com.MAQ.JournalApp.Entity.User;
import com.MAQ.JournalApp.Service.JournalEntryService;
import com.MAQ.JournalApp.Service.UserService;
import com.MAQ.JournalApp.Service.WeatherService;
import com.MAQ.JournalApp.api.response.WeatherResponse;
import com.MAQ.JournalApp.repository.UserRepo;
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

    @Autowired
    private WeatherService weatherService;


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

    @GetMapping("greet")
    public  ResponseEntity<?> greeting(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Noida");
        String greeting = "";
        if(weatherResponse!=null){
            greeting=" , Weather feels like "+weatherResponse.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hii "+authentication.getName()+ greeting,HttpStatus.OK);
    }
}

