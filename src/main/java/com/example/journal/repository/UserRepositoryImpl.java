package com.example.journal.repository;

import com.example.journal.entity.ConfigJournalApp;
import com.example.journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class UserRepositoryImpl  {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUsersForSA () {
        Query query =  new Query();
        query.addCriteria(Criteria.where("email").exists(true));
        query.addCriteria(Criteria.where("email").ne(null).ne(""));
        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

//        Criteria criteria = new Criteria();
//        query.addCriteria(criteria.orOperator(
//                Criteria.where("roles").in("ROLE_ADMIN"),
//                Criteria.where("roles").in("ROLE_USER")
//        ));

        List<User> users = mongoTemplate.find(query, User.class);
        return users;
    }

}
