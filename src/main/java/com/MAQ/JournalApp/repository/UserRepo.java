package com.MAQ.JournalApp.repository;

import com.MAQ.JournalApp.Entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByuserName(String username);

    void deleteByuserName(String username);
}
