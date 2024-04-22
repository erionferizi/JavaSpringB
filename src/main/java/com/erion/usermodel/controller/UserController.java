package com.erion.usermodel.controller;

import com.erion.usermodel.entity.User;
import com.erion.usermodel.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/all")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Integer id){
        return userService.getById(id);
    }

    @PostMapping("/user/save")
    public ResponseEntity saveUser(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/user/update/{id}")
    public ResponseEntity updateUser(@PathVariable("id") Integer id, @RequestBody User user){
        return userService.update(user, id);
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        userService.delete(id);
    }
}
