package com.glsi.xpress.Security;

import com.glsi.xpress.Entity.User;
import com.glsi.xpress.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class CustomUserDetailsService  implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
//        return new User(user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
    }

    /*@PostConstruct
    protected void initialize() {
        createDefaultAdminUser();
    }

    private void createDefaultAdminUser() {
        String defaultUsername = "admin";
        String defaultEmail = "admin@admin.com";
        String defaultPassword = "password";
        Role defaultRole = Role.ADMIN;

        // Check if the user already exists by username or email
        boolean userExists = userRepository.existsByUsername(defaultUsername) || userRepository.existsByEmail(defaultEmail);

        if (!userExists) {
            // If user doesn't exist, create and save the user
            UserEntity user = new UserEntity();
            user.setUsername(defaultUsername);
            user.setEmail(defaultEmail);
            user.setPassword(passwordEncoder.encode(defaultPassword));
            user.setRole(defaultRole);
            userRepository.save(user);
*/    }

