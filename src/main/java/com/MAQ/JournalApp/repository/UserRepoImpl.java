package com.MAQ.JournalApp.repository;

import com.MAQ.JournalApp.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepoImpl {

    @Autowired
    private MongoTemplate mongotemplate;


    public List<User> getUserForSA(){

        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$"));
        //query.addCriteria(Criteria.where("email").ne(null).ne(""));    //ne means not equal
        query.addCriteria(Criteria.where("SentimentAnalysis").is(true));
        List<User> users = mongotemplate.find(query,User.class);
        return  users;
    }

}
