package com.MAQ.JournalApp.Service;

import com.MAQ.JournalApp.Entity.User;
import com.MAQ.JournalApp.repository.UserRepo;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    //@Autowired
    //private UserService myuserservice;

    @Autowired
    private UserRepo myuserrepo;

    @Disabled     //to disable a testcase /
    @Test
    public void dummytest(){
        assertEquals(4,2+2);
        assertTrue(5>3);
    }

    @BeforeAll        // runs at starting , then all the test cases are executed.
    //@BeforeEach     // runs before each test case . similarly there are AfterAll and afterEach
    public static void dummy2(){
        assertEquals(1,1);
    }


    @CsvSource({
            "1,1,2",
            "10,2,12",
    })
    @ParameterizedTest
    public void test(int a ,int b,int sum){
        assertEquals(sum,a+b);
    }

    @ValueSource(strings = {
            "piyush",
            "Deepak",
    })
    @ParameterizedTest
    public void testfindUserByUserName(String username){
        assertNotNull(myuserrepo.findByuserName(username));
    }
}
