package com.example.journal.service;

import com.example.journal.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class userReposImpl {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Test
    void userRepositoryTests(){
        userRepository.getUsersForSA();

    }
}
