package com.maqsoft.journalApp.Service;

import com.maqsoft.journalApp.Entity.JournalEntry;
import com.maqsoft.journalApp.Entity.User;
import com.maqsoft.journalApp.repository.JournalEntryRepo;
import com.maqsoft.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepo UserRepo;

    private static final PasswordEncoder passwordencoder = new BCryptPasswordEncoder();

    public void saveentry(User myuser){
        UserRepo.save(myuser);
    }

    public void savenewuser(User myuser){
        myuser.setPassWord(passwordencoder.encode(myuser.getPassWord()));
        myuser.setRoles(Arrays.asList("user"));
        UserRepo.save(myuser);
    }

    public void saveadmin(User myuser){
        myuser.setPassWord(passwordencoder.encode(myuser.getPassWord()));
        myuser.setRoles(Arrays.asList("user","admin"));
        UserRepo.save(myuser);
    }

    public List<User> getAll(){
        return UserRepo.findAll();
    }

    public Optional<User> findbyid(ObjectId id){
        return UserRepo.findById(id);
    }

    public void deletebyId(ObjectId id){
        UserRepo.deleteById(id);
    }

    public User findbyUsername(String username){
        return UserRepo.findByuserName(username);
    }
}
