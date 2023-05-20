package com.nat_nav.natural_navigator.services;

import com.nat_nav.natural_navigator.entity.User;
import com.nat_nav.natural_navigator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public Optional<User> findByEmail(String Email){
        return userRepository.findByEmail(Email);
    }
}
