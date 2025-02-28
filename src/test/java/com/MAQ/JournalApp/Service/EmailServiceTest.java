package com.MAQ.JournalApp.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    private  EmailService emailservice;

    @Test
    void testSendMail(){
        emailservice.sendEmail("pklid471@gmail.com","java mail sending test","Hii , " +
                "this is a dummy mail just to test the java mail sender i build");
    }
}
