package com.example.journal.repository;

import com.example.journal.entity.ConfigJournalApp;
import com.example.journal.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigAppRepos extends MongoRepository<ConfigJournalApp, ObjectId> {


}
