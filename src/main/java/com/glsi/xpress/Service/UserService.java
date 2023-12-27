package com.glsi.xpress.Service;

import com.glsi.xpress.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User createUser(User user);
    User updateUser(User user);
    User getUserById(Long userId);
    List<User> getAllUsers();
    void deleteUser(Long userId);
    Boolean existsByEmail(String email);
    User getUserByEmail(String email);

    Optional<User> getUserByUsername(String username);
}