//package com.example.journal.service;
//
//import com.example.journal.repository.UserRepos;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatcher;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.test.context.bean.override.mockito.MockitoBean;
//
//import static org.mockito.Mockito.*;
//
//
//public class UserDetailsImplTests {
//    @InjectMocks
//    private UserDetailsImpl userDetailsImpl;
//
//    @MockitoBean
//    private UserRepos  userRepos;
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//    @Test
//    public void loadUserByUsernameTest() {
//        when(userRepos.findByusername(ArgumentMatchers.anyString())).thenReturn((com.example.journal.entity.User) User.builder().username("testuser").password("testpass").roles("USER").build());
//        UserDetails userDetails = userDetailsImpl.loadUserByUsername("testuser");
//        Assertions.assertNotNull(userDetails);
//    }
//}
