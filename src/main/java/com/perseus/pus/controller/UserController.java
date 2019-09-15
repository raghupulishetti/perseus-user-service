package com.perseus.pus.controller;

import com.perseus.pus.dto.Response;
import com.perseus.pus.dto.UserDTO;
import com.perseus.pus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users/{userId}")
    public Response<?> findUserById(@PathVariable("userId") Long userId){
        return userService.findById(userId);
    }

    @PostMapping("/users")
    public Response<?> saveUser(@Valid @RequestBody UserDTO user) {
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public Response<?> findUserByName(@RequestParam(value="name") String name){
        return userService.findUserByName(name);
    }

    @DeleteMapping("/users/{userId}")
    public Response<?> deleteUser(@PathVariable("userId") Long userId){
        return userService.deleteUser(userId);
    }

    @PutMapping("/users/{userId}/contact")
    public Response<?> updateContact(@RequestBody UserDTO userDTO, @PathVariable("userId") Long userId){
        return userService.updateContact(userDTO, userId);

    }

}
