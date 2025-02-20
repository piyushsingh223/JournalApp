package com.MAQ.JournalApp.Controller;


import com.MAQ.JournalApp.Entity.User;
import com.MAQ.JournalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService myuserservice;

    @GetMapping("get-all")
    public ResponseEntity<?> getAllUsers(){

        List<User> users = myuserservice.getAll();
        if(!users.isEmpty() && users!=null){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("create-admin")
    public void create_admin(@RequestBody User user){
        myuserservice.saveadmin(user);
    }

}

