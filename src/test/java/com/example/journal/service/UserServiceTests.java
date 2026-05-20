//package com.example.journal.service;
//
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
////@SpringBootTest
//public class UserServiceTests {
//
//    @Autowired
//    private UserService userService;
//
//    @Disabled
//    @Test
//    public void testFindUserByName() {
//        assertNotNull(userService.findByUserName("Pankaj"));
//    }
//
//    @ParameterizedTest
//    @CsvSource({
//        "1, 2, 3",
//        "2, 3, 5",
//        "3, 4, 9"
//    })
//    public void test(int a,int b,int c) {
//        assertEquals(c,a+b,"Failed for inputs: " + a + ", " + b + ", " + c);;
//    }
//}
