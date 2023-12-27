package com.glsi.xpress.Controller;

import com.glsi.xpress.DTO.SigninDTO;
import com.glsi.xpress.DTO.SignupDTO;
import com.glsi.xpress.Entity.Enum.URole;
import com.glsi.xpress.Entity.User;
import com.glsi.xpress.Exceptions.EmailAlreadyInUse;
import com.glsi.xpress.Repository.UserRepository;
import com.glsi.xpress.Security.JWTGenerator;
import com.glsi.xpress.Service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class SignController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    @Autowired
    public SignController(UserService userService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator, UserRepository userRepository) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    //Signup REST API endpoint
    @PostMapping("/signup")
    public ResponseEntity<User> register(@ModelAttribute @Valid SignupDTO signupDTO){
        if(userService.existsByEmail(signupDTO.getEmail())) {
            throw new EmailAlreadyInUse();
        }
        User user = new User();
        user.setUsername(signupDTO.getUsername());
        System.out.println("username: " + signupDTO);

        user.setPassword(passwordEncoder.encode(signupDTO.getPassword()));
        System.out.println("password: " + signupDTO.getPassword());

        user.setEmail(signupDTO.getEmail());
        System.out.println("email: " + signupDTO.getEmail());
        //set the role to member by default
        user.setRole(URole.USER);

        userService.createUser(user);

        return  ResponseEntity.ok(user);
    }

    //Signin REST API endpoint
    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody SigninDTO loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtGenerator.generateToken(authentication);
            Optional<User> user = userService.getUserByUsername(loginDto.getUsername());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("username", user.get().getUsername());
            response.put("role", user.get().getRole().toString());

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }


    //Signout REST API endpoint
    @PostMapping("/signout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return new ResponseEntity<>("You have been logged out successfully", HttpStatus.OK);
    }
}
