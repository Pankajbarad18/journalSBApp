package com.example.journal.service;

import com.example.journal.entity.User;
import com.example.journal.repository.UserRepos;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepos  userRepos;

    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        User user = userRepos.findByusername(username);
        if(user != null){
            return org.springframework.security.core.userdetails.User.builder().username(user.getUsername()).password(user.getPassword()).roles(user.getRoles().toArray(new String[0])).build();
        }
            throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
