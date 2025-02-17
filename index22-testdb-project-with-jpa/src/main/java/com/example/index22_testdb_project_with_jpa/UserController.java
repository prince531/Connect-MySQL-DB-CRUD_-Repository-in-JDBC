package com.example.index22_testdb_project_with_jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/jpa")
public class UserController {
    @Autowired
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id, User user) {
        if(userRepository.existsById(id)) {
         //return userRepository.findById(id).get().getId()+" "+userRepository.findById(id).get().getName();
        return userRepository.findById(id).get().toString();
        }
        else{
            return "User not found";
        }
    }

    @PostMapping
    public String createUser(@RequestBody User user) {
        userRepository.save(user);
        return "User created";

        // if(userRepository.existsById(user.getId())) {
        //     return "User already exists";
        // }
        // else{
        //     userRepository.save(user);
        //     return "User created";
        // }
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {
        if(userRepository.existsById(id)) {
            user.setId(id);
            userRepository.save(user);
            return "User updated";
        }
        else{
            return "User not found";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        if(userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User deleted";
        }
        else{
            return "User not found";
        }
    }   

}
