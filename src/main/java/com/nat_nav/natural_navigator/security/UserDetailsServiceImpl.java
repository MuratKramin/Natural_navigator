
package com.nat_nav.natural_navigator.security;

import com.nat_nav.natural_navigator.entity.User;
import com.nat_nav.natural_navigator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user= userRepository.findByEmail(username);
        if(user.isEmpty())throw new UsernameNotFoundException("user not found");
        return new com.nat_nav.natural_navigator.security.UserDetails(user.get());
    }
}

