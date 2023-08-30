package com.logicea.card.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.logicea.card.entity.User;

public interface UserService {
    User saveUser (User user);
    List<User> fetchAllUsers();
    User getUserById (long userId);
    User getUserByEmail (String email);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
