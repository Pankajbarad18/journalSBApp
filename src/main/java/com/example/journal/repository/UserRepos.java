package com.example.journal.repository;

import com.example.journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepos extends MongoRepository<User, ObjectId> {

    User findByusername(String userName);

    void deleteByusername(String currentUsername);
}
