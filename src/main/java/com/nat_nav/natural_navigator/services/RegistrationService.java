package com.nat_nav.natural_navigator.services;

import com.nat_nav.natural_navigator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nat_nav.natural_navigator.entity.User;

@Service
public class RegistrationService {
    private final UserRepository userRepository;
    @Autowired
    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional
    public void register(User user){
        userRepository.save(user);
    }
}
