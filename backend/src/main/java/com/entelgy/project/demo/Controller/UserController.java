package com.entelgy.project.demo.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.entelgy.project.demo.Entity.User;
import com.entelgy.project.demo.Service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    //PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        return userService.authenticate(user);
    }

}
