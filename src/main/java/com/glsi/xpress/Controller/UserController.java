package com.glsi.xpress.Controller;


import com.glsi.xpress.DTO.UserUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.glsi.xpress.Entity.User;
import com.glsi.xpress.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public User createUser(@RequestBody User user) {
        // set BooksBorrowed to 0
        user.setBooksBorrowed(0);
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PatchMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody UserUpdateDTO userUpdateDTO) {
        User existingUser = userService.getUserById(userId);
        if (existingUser == null) {
            return null;
        }

        // Map fields from the DTO to the existing user entity
        if (userUpdateDTO.getUsername() != null) {
            existingUser.setUsername(userUpdateDTO.getUsername());
        }
        if (userUpdateDTO.getEmail() != null) {
            existingUser.setEmail(userUpdateDTO.getEmail());
        }
        if (userUpdateDTO.getPassword() != null) {
            existingUser.setPassword(userUpdateDTO.getPassword());
        }
        // Handle other updatable fields similarly

        return userService.updateUser(existingUser);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

}

