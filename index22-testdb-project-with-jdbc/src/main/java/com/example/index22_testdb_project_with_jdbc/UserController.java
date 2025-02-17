package com.example.index22_testdb_project_with_jdbc;



import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/root")
public class UserController {
    
    UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @GetMapping
    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id){
        if(userRepository.existsById(id)){
            return userRepository.findById(id).get().toString();
        }
        else{
            return "User not found";
        }
    }

     //Spring data-jdbc does not support save() method / PostMapping / insert method 
    //and tabele nahi banata hai ye bani banae table me data insert karta hai

    @PutMapping("/{id}")
    public String updateByUser(@PathVariable int id,@RequestBody User user){
        if(userRepository.existsById(id)){
            user.setId(id);
            userRepository.save(user);
            return "User updated";
        }
        else{
            return "User not found";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable int id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return id+" User id deleted";
        }
        else{
            return id+" User id not found";
        }
    }

}
