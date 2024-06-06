package com.entelgy.project.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entelgy.project.demo.Entity.User;
import com.entelgy.project.demo.Repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean authenticate(User user) {
        String email = user.getEmail();
        String password = user.getPassword();

        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));

        if (userOptional.isPresent()) {
            User storedUser = userOptional.get();
            return storedUser.getPassword().equals(password);
        }

        return false;
    }
}