package com.maqsoft.journalApp.Service;

import com.maqsoft.journalApp.Entity.User;
import com.maqsoft.journalApp.repository.UserRepo;
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

    @Disabled
    @BeforeEach     //this is to intialize the userrepository else it will throw null exception error.
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Disabled
    @Test
    void loadUserByUsernameTest(){
        when(myuserepo.findByuserName(ArgumentMatchers.anyString()))
                .thenReturn(User.builder()
                        .userName("ram")
                        .passWord("ram123")
                        .roles(new ArrayList<>())
                        .build());
        UserDetails user = userdetailsserviceimpl.loadUserByUsername("ram");
        Assertions.assertNotNull(user);
    }

}
