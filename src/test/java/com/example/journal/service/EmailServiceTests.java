package com.example.journal.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    EmailService emailService;

    @Test
    public void testEmailService() {
        emailService.sendEmail("pankajbarad18@gmail.com","Java Spring Boot mail Implementation","Hi This is Implemented in Java Spring Boot Kindly Ignore this mail");
    }
}
