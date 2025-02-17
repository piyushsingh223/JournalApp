package com.maqsoft.journalApp.Controller;

import com.maqsoft.journalApp.Entity.User;
import com.maqsoft.journalApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService myuserService;


    @GetMapping("health-check")
    public String health_check(){
        return "okay";
    }


    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        //System.out.println(user);
        myuserService.savenewuser(user);
    }

}
