package com.example.journal.service;

import com.example.journal.entity.JournalEntity;
import com.example.journal.entity.User;
import com.example.journal.repository.UserRepos;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepos userRepos;



    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User addEntry(User user){
        logger.info("Attempting to add user: {}", user.getUsername());

        try {
            // Check if user already exists
//            User existingUser = userRepos.findByusername(user.getUsername());
//            if (existingUser != null) {
                logger.info("User {} already exists, returning existing user", user.getUsername());
                // Return existing user instead of throwing duplicate key error
//                return existingUser;
//            }
            User savedUser = userRepos.save(user);
            logger.info("Successfully saved user: {}", savedUser.getUsername());
            return savedUser;
        } catch (Exception e) {
            logger.error("Error saving user {}: {}", user.getUsername(), e.getMessage(), e);
            throw e;
        }

    }

    public User saveUser(User user){

        try {
            user.setPassword(Objects.requireNonNull(passwordEncoder.encode(user.getPassword())));
            user.setRoles(new ArrayList<>(List.of("USER")));
            return userRepos.save(user);
        } catch (Exception e) {
            logger.error("Error saving user {}: {}", user.getUsername(), e.getMessage(), e);
            throw e;
        }

    }

    public List<User> getAll(){
        return userRepos.findAll();
    }

    public Optional<User> findByUserName(String userName){
        return Optional.ofNullable(userRepos.findByusername(userName));
    }

//    public User updateById(ObjectId id,User newEntry){
//        User old = getById(id).orElse(null);
//        if (old != null){
////            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
////            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
//            userRepos.save(old);
//            return old;
//        }
//        return null;
//
//    }

    public void deleteById(ObjectId id){
        userRepos.deleteById(id);
    }

    public User findByusername(String username){
        return userRepos.findByusername(username);
    }

    public User saveAdmin(User user) {
        try {
            user.setPassword(Objects.requireNonNull(passwordEncoder.encode(user.getPassword())));
            user.setRoles(new ArrayList<>(List.of("USER","ADMIN")));
            return userRepos.save(user);
        } catch (Exception e) {
            logger.error("Error saving user {}: {}", user.getUsername(), e.getMessage(), e);
            throw e;
        }
    }
}
