package com.example.journal.controller;

import com.example.journal.apiResponse.WeatherResponse;
import com.example.journal.entity.User;
import com.example.journal.repository.UserRepos;
import com.example.journal.service.UserService;
import com.example.journal.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepos userRepos;

    @Autowired
    private WeatherService weatherService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

//    @GetMapping
//    public ResponseEntity<?> getAllUsers(){
//        List<User> all = userService.getAll();
//        logger.info("Fetching all users: {}", all);
//        if(all !=null && !all.isEmpty()) {
//            return new ResponseEntity<List<User>>(all, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }



    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        SecurityContext securityContext = SecurityContextHolder.getContext();
        String currentUsername = Objects.requireNonNull(securityContext.getAuthentication()).getName();

        User userInDB = userService.findByUserName(currentUsername).orElse(null);
        logger.info("Updating user: {}", user);
        if(userInDB != null){
            userInDB.setUsername(user.getUsername());
            userInDB.setPassword(user.getPassword());
            userService.saveUser(userInDB);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        Authentication securityContext = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = Objects.requireNonNull(securityContext).getName();
        userRepos.deleteByusername(currentUsername);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/greetings")
    public ResponseEntity<?> getcurrent(){
        Authentication securityContext = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = Objects.requireNonNull(securityContext).getName();
        WeatherResponse weatherRes= weatherService.getWeather("Delhi");
        String greetings = "";
        if(weatherRes != null){
            greetings = " ,weather feels like "+weatherRes.getCurrent().getFeelslike();
        }
        return new ResponseEntity<>("Hi " + currentUsername + greetings, HttpStatus.OK);
    }
}
