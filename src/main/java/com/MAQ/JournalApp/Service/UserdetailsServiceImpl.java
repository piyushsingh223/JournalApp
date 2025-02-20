package com.MAQ.JournalApp.Service;

import com.MAQ.JournalApp.Entity.User;
import com.MAQ.JournalApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserdetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo myuserRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = myuserRepo.findByuserName(username);
        if(user!=null){
            UserDetails userdetails=  org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUserName())
                    .password(user.getPassWord())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
            return userdetails;
        }
        throw new UsernameNotFoundException("user not found with username:"+username);
    }
}
