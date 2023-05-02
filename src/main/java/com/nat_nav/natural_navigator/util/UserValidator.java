package com.nat_nav.natural_navigator.util;

import com.nat_nav.natural_navigator.entity.User;
import com.nat_nav.natural_navigator.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public UserValidator(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            userDetailsService.loadUserByUsername(user.getEmail());
        } catch (UsernameNotFoundException ignored){
            return;
        }
        errors.rejectValue("username","","Такое имя пользлователя уже существует.");
    }
}
