package com.substring.Irctc.config.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class CustomizeUserDetailService  implements UserDetailsService {



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       UserDetails user= User.builder()
               .username("user")
               .password("{noop}user123")
               .roles("User")
               .build();

        if(user.getUsername().equals(username)){
            return user;
        }
        else{
            throw new UsernameNotFoundException("user not found with username "+username);
        }
    }
}
