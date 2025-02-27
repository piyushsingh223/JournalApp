package com.MAQ.JournalApp.Repository;

import com.MAQ.JournalApp.repository.UserRepoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepoImplTest {

    @Autowired
    private UserRepoImpl userrepoimpl;

    @Test
    public void TestNewUser(){
        Assertions.assertNotNull(userrepoimpl.getUserForSA());
    }
}
