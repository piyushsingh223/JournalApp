package com.MAQ.JournalApp.Service;

import com.MAQ.JournalApp.Entity.User;
import com.MAQ.JournalApp.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserdetailsServiceImpl userdetailsserviceimpl;

    @Mock
    private UserRepo myuserepo;

//    @BeforeEach
//    void setup(){
//        try (AutoCloseable closeable = MockitoAnnotations.openMocks(this)) {
//            // Your test logic here
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    @BeforeEach     //this is to intialize the userrepository else it will throw null exception error.
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){
        when(myuserepo.findByuserName(ArgumentMatchers.anyString()))
                .thenReturn(new User("ram","ram123",new ArrayList<>(),new ArrayList<>()));
        UserDetails user = userdetailsserviceimpl.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }

}
