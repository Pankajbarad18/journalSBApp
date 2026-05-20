package com.example.journal.repository;

import com.example.journal.entity.JournalEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepos extends MongoRepository<JournalEntity, ObjectId> {
}
