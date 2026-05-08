package com.daniels.springboot.auth;

import org.springframework.security.access.method.P;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
@RequestMapping("/auth")
public class JwtController {
        private final JwtService jwtService;
        private final EventUserDetailsService EventUserDetailsService;
        private final AuthenticationManager authenticationManager;

    public JwtController(JwtService jwtService, EventUserDetailsService userDetailsService,
            AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.EventUserDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {

        System.out.println("Login attempt for user: " + authRequest.getUsername());
        //Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        //Load user details (db lookup or in-memory)      
        UserDetails userDetails = EventUserDetailsService.loadUserByUsername(authRequest.getUsername());

        //Generate JWT token
        return jwtService.generateToken(userDetails.getUsername());
    }
    @PostMapping("/register")
    public String register(@RequestBody AuthRequest authRequest, HttpServletRequest request) {
        System.out.println("REQUEST URI: " + request.getRequestURI());
        System.out.println("METHOD: " + request.getMethod());
        System.out.println("Register attempt for user: " + authRequest.getUsername());
        System.out.println("Register attempt for user: " + authRequest.getUsername());
        User newUser = new User();
        newUser.setUsername(authRequest.getUsername());
        newUser.setPassword(authRequest.getPassword());
        newUser.setRole("USER");
        EventUserDetailsService.register(newUser);
        return "User registered successfully";
    }
}
