package com.maqsoft.journalApp.Controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class HealthCheck {

    @GetMapping("/health-check")
    public String healthcheck(){
        return "hello";
    }

}
