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

    /**
     * This method is to find the user with the given user id.
     * @param userId
     * @return
     */
    @GetMapping("/users/{userId}")
    public Response<?> findUserById(@PathVariable("userId") Long userId){
        return userService.findById(userId);
    }

    /**
     * This method is to save the new user contact information
     * @param user
     * @return
     */
    @PostMapping("/users")
    public Response<?> saveUser(@Valid @RequestBody UserDTO user) {
        return userService.saveUser(user);
    }


    /**
     * This method is to find user by given name. Name can be "firstname lastname"
     * @param name
     * @return
     */
    @GetMapping("/users")
    public Response<?> findUserByName(@RequestParam(value="name") String name){
        return userService.findUserByName(name);
    }

    /**
     * This method is used to delete the user information.
     * @param userId
     * @return
     */
    @DeleteMapping("/users/{userId}")
    public Response<?> deleteUser(@PathVariable("userId") Long userId){
        return userService.deleteUser(userId);
    }

    /**
     * This method is used to update the existing user information.
     * @param userDTO
     * @param userId
     * @return
     */
    @PutMapping("/users/{userId}/contact")
    public Response<?> updateContact(@RequestBody UserDTO userDTO, @PathVariable("userId") Long userId){
        return userService.updateContact(userDTO, userId);

    }

}
