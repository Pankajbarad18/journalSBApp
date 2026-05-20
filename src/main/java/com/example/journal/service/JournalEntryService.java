package com.example.journal.service;

import com.example.journal.entity.JournalEntity;
import com.example.journal.entity.User;
import com.example.journal.repository.JournalEntryRepos;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepos journalRepos;

    @Autowired
    private UserService userService;

    @Transactional
    public void addEntry(JournalEntity journalEntity,String username){
        try {
            User user = userService.findByUserName(username).orElse(null);
            if(user != null) {
                JournalEntity journal =  journalRepos.save(journalEntity);
                user.getJournalEntityList().add(journal);
                userService.addEntry(user);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public List<JournalEntity> getAll(){
        return journalRepos.findAll();
    }

    public Optional<JournalEntity> getById(ObjectId id){
        return journalRepos.findById(id);
    }

//    public JournalEntity updateById(ObjectId id,JournalEntity newEntry){
//        JournalEntity old = getById(id).orElse(null);
//        if (old != null){
//
//        }
//        return null;
//
//    }

    @Transactional
    public boolean deleteById(ObjectId id,String username) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(username).orElse(null);
            if (user != null) {
                removed = user.getJournalEntityList().removeIf(journalEntity -> journalEntity.getId().equals(id));
                if (removed) {
                    userService.addEntry(user);
                    journalRepos.deleteById(id);
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
        return removed;
    }


}
