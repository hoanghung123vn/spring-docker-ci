package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/users")
public class UserController {
    private UserRepository userRepository;
    @Autowired
    public UserController (UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @GetMapping
    public Iterable<User> getAll() {
        return userRepository.findAll();
    }
    @GetMapping("/{id}")
    public User get(@PathVariable(name = "id") int id) {
        return userRepository.findById(id).orElse(new User());
    }
    @DeleteMapping("/{id}")
    public void delete (@PathVariable(name = "id") int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null)
            userRepository.deleteById(id);
    }
    @PostMapping
    public User create(@RequestBody User user) {
        return userRepository.save(user);
    }
    @PutMapping("/{id}")
    public User create(@RequestBody User user, @PathVariable(name = "id") int id) {
        User oldUser = userRepository.findById(id).orElse(null);
        if (oldUser != null) {
            oldUser.setEmail(user.getEmail());
            oldUser.setPassword(user.getPassword());
            userRepository.save(oldUser);
        }
        return oldUser;
    }
}
