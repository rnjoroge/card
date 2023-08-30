package com.logicea.card.service;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import com.logicea.card.entity.User;
import com.logicea.card.repository.UserRepository;
import com.logicea.card.security.services.UserDetailsImpl;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> fetchAllUsers() {
        return (List<User>)
        userRepository.findAll();
    }

    @Override
    public User getUserById(long userId) {
        return  userRepository.findById(userId)
              .get();
    }

    @Override
    public User getUserByEmail(String email) {
        User user = new User();  
             user.setEmail(email);
        Example<User> example = Example.of(user);     
          return  userRepository.findOne(example)
              .get();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }
    
}
