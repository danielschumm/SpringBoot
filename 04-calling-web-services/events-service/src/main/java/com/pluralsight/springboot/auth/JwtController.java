package com.pluralsight.springboot.auth;

import org.springframework.security.access.method.P;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@RestController
@RequestMapping("/auth")
public class JwtController {
        private final JwtService jwtService;
        private final UserDetailsService userDetailsService;
        private final AuthenticationManager authenticationManager;

    public JwtController(JwtService jwtService, UserDetailsService userDetailsService,
            AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String login(@RequestBody AuthRequest authRequest) {

        //Authenticate the user
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        //Load user details        
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        //Generate JWT token
        return jwtService.generateToken(userDetails.getUsername());
    }
}
